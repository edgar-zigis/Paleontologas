package com.zigis.paleontologas.features.library.stories.geologicalperiod

import java.io.Serializable

@kotlinx.serialization.Serializable
data class GeologicalPeriodConfiguration(
    val periodId: Int
) : Serializable