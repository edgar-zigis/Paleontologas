package com.zigis.paleontologas.features.library.stories.timeline

import com.zigis.paleontologas.core.architecture.interfaces.IIntent

sealed class TimelineScreenIntent : IIntent {
    data object Initialize : TimelineScreenIntent()
    data class OpenPeriod(val periodId: Int) : TimelineScreenIntent()
}