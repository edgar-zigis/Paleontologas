package com.zigis.paleontologas.features.library.stories.timeline

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.library.factories.TimelineListItemFactory
import com.zigis.paleontologas.features.library.routing.LibraryRouter
import com.zigis.paleontologas.features.library.stories.timeline.TimelineScreenIntent.*

class TimelineViewModel(
    private val libraryRouter: LibraryRouter,
    private val timelineListItemFactory: TimelineListItemFactory
) : BaseViewModel<TimelineScreenState, TimelineScreenIntent>() {

    override fun getInitialData() = TimelineScreenState()

    override suspend fun handleIntent(intent: TimelineScreenIntent) {
        when (intent) {
            is Initialize -> initialize()
            is OpenPeriod -> libraryRouter.openPeriod(periodId = intent.periodId)
        }
    }

    private suspend fun initialize() {
        updateState {
            it.copy(
                periodItems = timelineListItemFactory.getItems(),
                animateLayoutChanges = it.animateLayoutChanges == null
            )
        }
    }
}