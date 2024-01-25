package com.zigis.paleontologas.features.quiz.stories.mark

import com.zigis.paleontologas.core.architecture.v2.interfaces.IIntent

sealed class QuizMarkIntent : IIntent {
    data class Initialize(val mark: Int) : QuizMarkIntent()
}