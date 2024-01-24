package com.zigis.paleontologas.features.quiz.stories.progress

import com.zigis.paleontologas.core.architecture.v2.interfaces.IState

data class QuizProgressViewState(
    val progress: Float = 0f
) : IState