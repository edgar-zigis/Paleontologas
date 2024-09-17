package com.zigis.paleontologas.features.library.stories.geologicalperiod

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.library.repositories.PeriodRepository
import com.zigis.paleontologas.features.library.routing.LibraryRouter
import com.zigis.paleontologas.features.library.factories.GeologicalPeriodListItemFactory
import com.zigis.paleontologas.features.library.stories.geologicalperiod.GeologicalPeriodScreenIntent.*
import kotlinx.coroutines.delay

class GeologicalPeriodViewModel(
    private val libraryRouter: LibraryRouter,
    private val periodRepository: PeriodRepository,
    private val geologicalPeriodListItemFactory: GeologicalPeriodListItemFactory
) : BaseViewModel<GeologicalPeriodScreenState, GeologicalPeriodScreenIntent>() {

    override fun getInitialData() = GeologicalPeriodScreenState()

    override suspend fun handleIntent(intent: GeologicalPeriodScreenIntent) {
        when (intent) {
            is Initialize -> initialize(periodId = intent.periodId)
            is OpenLifeForm -> libraryRouter.openLifeForm(intent.lifeFormId)
            is InvokeBack -> libraryRouter.popCurrentScreen()
        }
    }

    private suspend fun initialize(periodId: Int) {
        val fetchedPeriod = periodRepository.findOne(periodId)
        updateState {
            it.copy(
                periodId = fetchedPeriod.id,
                title = fetchedPeriod.title,
                artwork = fetchedPeriod.artwork,
                artworkAuthor = fetchedPeriod.artworkAuthor,
                timeScale = fetchedPeriod.timeScale,
                environmentDescription = fetchedPeriod.environmentDescription,
                description = fetchedPeriod.description,
                map = fetchedPeriod.map,
                additionalTitle = fetchedPeriod.additionalTitle,
                additionalDescription = fetchedPeriod.additionalDescription,
                lifeFormDescription = fetchedPeriod.lifeFormDescription
            )
        }
        delay(350)
        updateState {
            it.copy(
                lifeFormItems = geologicalPeriodListItemFactory.getItems(periodId = periodId)
            )
        }
    }
}