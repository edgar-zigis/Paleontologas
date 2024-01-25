package com.zigis.paleontologas.features.main.stories.home

import com.zigis.paleontologas.core.architecture.v2.interfaces.IState
import com.zigis.paleontologas.features.library.data.Period

data class HomeViewState(
    val periodList: List<Period> = emptyList(),
    val animateLayoutChanges: Boolean? = null
) : IState