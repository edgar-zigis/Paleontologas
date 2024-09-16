package com.zigis.paleontologas.features.main.stories.home

import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.features.main.stories.home.list.HomeListItem

data class HomeViewState(
    val periodItems: List<HomeListItem> = emptyList(),
    val animateLayoutChanges: Boolean? = null
) : IState