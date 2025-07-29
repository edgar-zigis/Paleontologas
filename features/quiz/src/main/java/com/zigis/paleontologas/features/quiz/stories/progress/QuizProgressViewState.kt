package com.zigis.paleontologas.features.quiz.stories.progress

import com.google.firebase.auth.FirebaseUser
import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.features.quiz.entities.QuizPlayer
import java.util.Locale

data class QuizProgressViewState(
    val activeUser: FirebaseUser? = null,
    val allCountries: List<Locale> = emptyList(),
    val createUserNameNeeded: Boolean = false,
    val errorDescription: String? = null,
    val players: List<QuizPlayer> = emptyList(),
    val globalRanking: Int = 0,
    val progress: Float = 0f,
    val isLoading: Boolean = false
) : IState