package com.zigis.paleontologas.features.quiz.stories.finalresult

import com.zigis.paleontologas.core.architecture.interfaces.IIntent

sealed class QuizFinalResultIntent : IIntent {
    data class Initialize(val mark: Int) : QuizFinalResultIntent()
    data object InvokeBack : QuizFinalResultIntent()
}