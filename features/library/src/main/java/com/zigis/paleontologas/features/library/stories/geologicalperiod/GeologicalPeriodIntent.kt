package com.zigis.paleontologas.features.library.stories.geologicalperiod

import com.zigis.paleontologas.core.architecture.interfaces.IIntent

sealed class GeologicalPeriodIntent : IIntent {
    data class Initialize(val periodId: Int) : GeologicalPeriodIntent()
    data class OpenLifeForm(val lifeFormId: Int) : GeologicalPeriodIntent()
    data object InvokeBack : GeologicalPeriodIntent()
}