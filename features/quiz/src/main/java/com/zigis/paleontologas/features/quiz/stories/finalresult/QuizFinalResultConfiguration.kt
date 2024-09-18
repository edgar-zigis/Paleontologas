package com.zigis.paleontologas.features.quiz.stories.finalresult

import java.io.Serializable

@kotlinx.serialization.Serializable
data class QuizFinalResultConfiguration(
    val mark: Int
) : Serializable