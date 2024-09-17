package com.zigis.paleontologas.features.library.stories.lifeform

import java.io.Serializable

@kotlinx.serialization.Serializable
data class LifeFormConfiguration(
    val lifeFormId: Int
) : Serializable