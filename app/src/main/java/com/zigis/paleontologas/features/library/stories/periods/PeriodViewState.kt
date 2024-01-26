package com.zigis.paleontologas.features.library.stories.periods

import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.features.library.data.LifeForm

data class PeriodViewState(
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
    val lifeForms: List<LifeForm> = emptyList()
) : IState