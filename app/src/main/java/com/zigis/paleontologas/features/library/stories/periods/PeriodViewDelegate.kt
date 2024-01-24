package com.zigis.paleontologas.features.library.stories.periods

import com.zigis.paleontologas.features.library.data.LifeForm

interface PeriodViewDelegate {
    fun openLifeForm(lifeForm: LifeForm)
    fun onBackInvoked()
}