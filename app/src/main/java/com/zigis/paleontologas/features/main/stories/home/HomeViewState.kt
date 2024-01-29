package com.zigis.paleontologas.features.main.stories.home

import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.features.library.data.Period
import com.zigis.paleontologas.features.main.stories.home.adapter.HomeListAdapterItem

data class HomeViewState(
    val periodItems: List<HomeListAdapterItem> = emptyList(),
    val animateLayoutChanges: Boolean? = null
) : IState