package com.zigis.paleontologas.features.library.stories.formavitae

import com.zigis.paleontologas.core.architecture.interfaces.IState

data class FormaVitaeViewState(
    val title: String = "",
    val artwork: String = "",
    val artworkAuthor: String = "",
    val timeScale: String = "",
    val description: String = "",
    val additionalArtworkAuthor: String = "",
    val additionalArtwork: String = ""
) : IState