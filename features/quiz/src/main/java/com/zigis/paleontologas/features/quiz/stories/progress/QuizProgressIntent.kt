package com.zigis.paleontologas.features.quiz.stories.progress

import com.zigis.paleontologas.core.architecture.interfaces.IIntent

sealed class QuizProgressIntent : IIntent {
    data object Initialize : QuizProgressIntent()
    data object StartQuiz : QuizProgressIntent()
    data object CreateAccount : QuizProgressIntent()
    data class SetUsername(val value: String, val countryCode: String) : QuizProgressIntent()
    data object InvokeBack : QuizProgressIntent()
}