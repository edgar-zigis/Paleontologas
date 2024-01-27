package com.zigis.paleontologas.features.library.stories.periods

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.library.repositories.PeriodRepository
import com.zigis.paleontologas.features.library.routers.LibraryRouter
import com.zigis.paleontologas.features.library.usecases.LifeFormListUseCase
import com.zigis.paleontologas.features.library.stories.periods.PeriodIntent.*
import kotlinx.coroutines.delay

class PeriodViewModel(
    private val libraryRouter: LibraryRouter,
    private val periodRepository: PeriodRepository,
    private val lifeFormListUseCase: LifeFormListUseCase
) : BaseViewModel<PeriodViewState, PeriodIntent>() {

    override fun getInitialData() = PeriodViewState()

    override suspend fun handleIntent(intent: PeriodIntent) {
        when (intent) {
            is Initialize -> initialize(periodId = intent.periodId)
            is OpenLifeForm -> libraryRouter.openLifeForm(intent.lifeForm.id)
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
                lifeForms = lifeFormListUseCase.getPeriodItems(periodId)
            )
        }
    }
}