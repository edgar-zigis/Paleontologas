package com.zigis.paleontologas.features.library.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LifeForm(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val periodId: Int,
    val title: String,
    val description: String,
    val timeScale: String,
    val thumbnail: String,
    val artwork: String,
    val additionalArtwork: String,
    val artworkAuthor: String,
    val additionalArtworkAuthor: String
)