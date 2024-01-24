package com.zigis.paleontologas.features.library.stories.periods

import com.zigis.paleontologas.core.architecture.v2.BaseViewModel
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.library.repositories.PeriodRepository
import com.zigis.paleontologas.features.library.stories.lifeforms.LifeFormFragment
import com.zigis.paleontologas.features.library.usecases.LifeFormListUseCase
import kotlinx.coroutines.delay

class PeriodViewModel(
    private val globalRouter: GlobalRouter,
    private val periodRepository: PeriodRepository,
    private val lifeFormListUseCase: LifeFormListUseCase
) : BaseViewModel<PeriodViewState, PeriodIntent>() {

    override fun getInitialData() = PeriodViewState()

    override suspend fun handleIntent(intent: PeriodIntent) {
        when (intent) {
            is PeriodIntent.Initialize -> initialize(periodId = intent.periodId)
            is PeriodIntent.OpenLifeForm -> globalRouter.pushFragment(
                LifeFormFragment().also {
                    it.lifeFormId = intent.lifeForm.id
                }
            )
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