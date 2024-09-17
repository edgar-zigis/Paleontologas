package com.zigis.paleontologas.features.library.stories.geologicalperiod

import com.zigis.paleontologas.core.architecture.interfaces.IIntent

sealed class GeologicalPeriodScreenIntent : IIntent {
    data class Initialize(val periodId: Int) : GeologicalPeriodScreenIntent()
    data class OpenLifeForm(val lifeFormId: Int) : GeologicalPeriodScreenIntent()
    data object InvokeBack : GeologicalPeriodScreenIntent()
}