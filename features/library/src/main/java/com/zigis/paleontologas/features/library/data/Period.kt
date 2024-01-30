package com.zigis.paleontologas.features.library.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Period(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val additionalTitle: String,
    val shortDescription: String,
    val description: String,
    val additionalDescription: String,
    val timeScale: String,
    val environmentDescription: String,
    val lifeFormDescription: String,
    val thumbnail: String,
    val artwork: String,
    val artworkAuthor: String,
    val map: String
) {
    @Ignore
    var quizProgress: Int = 0
}