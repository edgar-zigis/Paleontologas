package com.zigis.paleontologas.features.library.stories.timeline

import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.features.library.stories.timeline.list.TimelineListItem

data class TimelineScreenState(
    val periodItems: List<TimelineListItem> = emptyList(),
    val animateLayoutChanges: Boolean? = null
) : IState