package com.zigis.paleontologas.features.library.stories.periods

import com.zigis.paleontologas.core.architecture.interfaces.IIntent
import com.zigis.paleontologas.features.library.data.LifeForm

sealed class PeriodIntent : IIntent {
    data class Initialize(val periodId: Int) : PeriodIntent()
    data class OpenLifeForm(val lifeForm: LifeForm) : PeriodIntent()
}