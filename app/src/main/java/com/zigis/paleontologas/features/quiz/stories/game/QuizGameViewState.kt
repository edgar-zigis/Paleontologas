package com.zigis.paleontologas.features.quiz.stories.game

import com.zigis.paleontologas.core.architecture.v2.interfaces.IState
import com.zigis.paleontologas.features.quiz.data.Question

data class QuizGameViewState(
    val question: Question? = null
) : IState