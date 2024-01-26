package com.zigis.paleontologas.features.library.stories.lifeforms

import com.zigis.paleontologas.core.architecture.interfaces.IIntent

sealed class LifeFormIntent : IIntent {
    data class Initialize(val lifeFormId: Int) : LifeFormIntent()
}