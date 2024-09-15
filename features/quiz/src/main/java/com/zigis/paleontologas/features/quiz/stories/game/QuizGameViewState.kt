package com.zigis.paleontologas.features.quiz.stories.game

import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.features.quiz.data.Question

data class QuizGameViewState(
    val question: Question? = null,
    val chosenOption: Int? = null,
    val correctOption: Int? = null
) : IState