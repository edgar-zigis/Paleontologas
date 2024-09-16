package com.zigis.paleontologas.features.main.stories.home.list

data class HomeListItem(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val shortDescription: String,
    val timeScale: String,
    val quizProgress: Int
)