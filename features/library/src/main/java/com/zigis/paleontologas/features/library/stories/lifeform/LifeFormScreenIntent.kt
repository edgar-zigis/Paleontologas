package com.zigis.paleontologas.features.library.stories.lifeform

import com.zigis.paleontologas.core.architecture.interfaces.IIntent

sealed class LifeFormScreenIntent : IIntent {
    data class Initialize(val lifeFormId: Int) : LifeFormScreenIntent()
    data object InvokeBack : LifeFormScreenIntent()
}