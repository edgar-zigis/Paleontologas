package com.zigis.paleontologas.features.quiz.factories

import com.zigis.paleontologas.features.quiz.entities.QuizPlayer
import com.zigis.paleontologas.features.quiz.managers.CountryManager
import com.zigis.paleontologas.features.quiz.managers.FirebaseDataManager

class QuizPlayerFactory(
    private val countryManager: CountryManager,
    private val firebaseDataManager: FirebaseDataManager
) {
    suspend fun getItems(): List<QuizPlayer> {
        val leaderboard = firebaseDataManager.fetchLeaderboard()
        return leaderboard.mapIndexed { index, item ->
            QuizPlayer(
                id = item.id,
                name = item.name,
                country = countryManager.getEmoji(item.country),
                points = item.xp,
                avatar = getAvatar(index),
                ranking = index + 1
            )
        }
    }

    private fun getAvatar(position: Int): String {
        return when (position) {
            0 -> "🐲"
            1 -> "🦁"
            2 -> "🐙"
            else -> ""
        }
    }
}
