package com.zigis.paleontologas.features.quiz.entities

data class QuizPlayer(
    val id: String,
    val name: String,
    val country: String,
    val points: Int,
    val avatar: String,
    val ranking: Int
)