package com.zigis.paleontologas.features.quiz.managers

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.zigis.paleontologas.core.BuildConfig
import com.zigis.paleontologas.core.extensions.sanitized
import kotlinx.coroutines.tasks.await

class FirebaseDataManager(
    private val applicationContext: Context
) {
    var user: FirebaseUser? = null

    fun isAuthenticated(): Boolean {
        return user != null
    }

    suspend fun needsUsername(): Boolean {
        return user?.let {
            !leaderboardEntryAlreadyExists()
        } ?: false
    }

    suspend fun authenticate(): FirebaseUser? {
        val credentialManager = CredentialManager.create(applicationContext)
        val googleOptions = GetSignInWithGoogleOption.Builder(
            serverClientId = BuildConfig.GOOGLE_SERVER_CLIENT_ID
        )
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleOptions)
            .build()

        try {
            val response = credentialManager.getCredential(applicationContext, request)
            val customCredential = response.credential as? CustomCredential
                ?: throw Exception("Unexpected credential type")

            if (customCredential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(customCredential.data)
                val credential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                val auth = FirebaseAuth.getInstance().signInWithCredential(credential).await()
                user = auth.user
                return user
            } else {
                throw Exception("Unexpected credential type")
            }
        } catch (exception: Exception) {
            throw exception
        }
    }

    suspend fun createInitialEntryIfNeeded(displayName: String, countryCode: String) {
        val user = user ?: throw Exception("Not authenticated")
        if (!leaderboardEntryAlreadyExists()) {
            val dbRef = FirebaseDatabase.getInstance().reference
            val nameRef = dbRef.child("names").child(displayName)
            val blacklistRef = dbRef.child("blacklist").child(displayName.sanitized())

            if (nameRef.get().await().exists()) throw Exception("Name exists")
            if (blacklistRef.get().await().exists()) throw Exception("Name blacklisted")

            val entry = mapOf("id" to user.uid, "name" to displayName, "country" to countryCode, "xp" to 0)

            val updates = mapOf(
                "/leaderboard/${user.uid}" to entry,
                "/names/$displayName" to user.uid
            )

            dbRef.updateChildren(updates).await()
        }
    }

    suspend fun leaderboardEntryAlreadyExists(): Boolean {
        val usr = user ?: throw Exception("Not authenticated")
        return FirebaseDatabase.getInstance()
            .getReference("leaderboard/${usr.uid}")
            .get()
            .await()
            .exists()
    }

    suspend fun fetchLeaderboard(): List<LeaderboardEntry> {
        val snapshot = FirebaseDatabase.getInstance()
            .getReference("leaderboard")
            .orderByChild("xp")
            .get()
            .await()

        return snapshot.children.mapNotNull { c ->
            val d = c.value as? Map<*,*> ?: return@mapNotNull null
            LeaderboardEntry(
                id = d["id"] as? String ?: return@mapNotNull null,
                name = d["name"] as? String ?: return@mapNotNull null,
                country = d["country"] as? String ?: return@mapNotNull null,
                xp = (d["xp"] as? Long)?.toInt() ?: 0
            )
        }.reversed()
    }

    suspend fun addXP(xp: Int) {
        val usr = user ?: throw Exception("Not authenticated")
        val current = getXP(usr.uid)
        FirebaseDatabase.getInstance()
            .getReference("leaderboard/${usr.uid}")
            .updateChildren(mapOf("xp" to current + xp))
            .await()
    }

    private suspend fun getXP(uid: String): Int {
        val snap = FirebaseDatabase.getInstance()
            .getReference("leaderboard/$uid")
            .get()
            .await()

        val d = snap.value as? Map<*,*> ?: throw Exception("XP missing")
        return (d["xp"] as? Long)?.toInt() ?: 0
    }

    data class LeaderboardEntry(
        val id: String, val name: String, val country: String, val xp: Int
    )
}