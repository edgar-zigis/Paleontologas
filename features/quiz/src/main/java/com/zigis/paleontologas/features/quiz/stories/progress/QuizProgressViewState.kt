package com.zigis.paleontologas.features.quiz.stories.progress

import com.google.firebase.auth.FirebaseUser
import com.zigis.paleontologas.core.architecture.interfaces.IState
import java.util.Locale

data class QuizProgressViewState(
    val activeUser: FirebaseUser? = null,
    val allCountries: List<Locale> = emptyList(),
    val createUserNameNeeded: Boolean = false,
    val progress: Float = 0f
) : IState