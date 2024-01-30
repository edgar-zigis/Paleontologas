package com.zigis.paleontologas.features.library.stories.geologicalperiod

import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.features.library.stories.geologicalperiod.adapter.GeologicalPeriodListAdapterItem

data class GeologicalPeriodViewState(
    val periodId: Int = 0,
    val title: String = "",
    val artwork: String = "",
    val artworkAuthor: String = "",
    val timeScale: String = "",
    val environmentDescription: String = "",
    val description: String = "",
    val map: String = "",
    val additionalTitle: String = "",
    val additionalDescription: String = "",
    val lifeFormDescription: String = "",
    val lifeFormItems: List<GeologicalPeriodListAdapterItem> = emptyList()
) : IState