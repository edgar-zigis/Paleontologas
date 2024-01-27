package com.zigis.paleontologas.features.quiz.stories.mark

import com.zigis.paleontologas.core.architecture.interfaces.IState

data class QuizMarkViewState(
    val mark: Int = 0,
    val totalQuestions: Int = 0
) : IState