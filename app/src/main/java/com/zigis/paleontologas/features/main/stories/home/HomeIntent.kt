package com.zigis.paleontologas.features.main.stories.home

import com.zigis.paleontologas.core.architecture.v2.interfaces.IIntent

sealed class HomeIntent : IIntent {
    data object Initialize : HomeIntent()
    data class OpenPeriod(val periodId: Int) : HomeIntent()
    data object OpenQuiz : HomeIntent()
    data object OpenLanguages : HomeIntent()
    data object OpenAbout : HomeIntent()
}