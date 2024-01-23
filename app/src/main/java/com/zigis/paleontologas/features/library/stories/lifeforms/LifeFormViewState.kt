package com.zigis.paleontologas.features.library.stories.lifeforms

import com.zigis.paleontologas.core.architecture.v2.interfaces.IState

data class LifeFormViewState(
    val title: String = "",
    val artwork: String = "",
    val artworkAuthor: String = "",
    val timeScale: String = "",
    val description: String = "",
    val additionalArtworkAuthor: String = "",
    val additionalArtwork: String = ""
) : IState