package com.zigis.paleontologas.features.library.stories.timeline.list

import androidx.compose.runtime.Stable

@Stable
data class TimelineListItem(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val shortDescription: String,
    val timeScale: String,
    val quizProgress: Int
)