package com.zigis.paleontologas.features.quiz.stories.mark

import com.zigis.paleontologas.core.architecture.v2.interfaces.IState

data class QuizMarkViewState(
    val mark: Int = 0
) : IState