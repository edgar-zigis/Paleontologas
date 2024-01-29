package com.zigis.paleontologas.features.library.stories.formavitae

import com.zigis.paleontologas.core.architecture.interfaces.IIntent

sealed class FormaVitaeIntent : IIntent {
    data class Initialize(val lifeFormId: Int) : FormaVitaeIntent()
    data object InvokeBack : FormaVitaeIntent()
}