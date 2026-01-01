package com.zigis.paleontologas.features.quiz.stories.game

import com.zigis.paleontologas.features.quiz.data.Question
import java.io.Serializable

@kotlinx.serialization.Serializable
data class QuizGameConfiguration(
    val category: Question.Category?
) : Serializable