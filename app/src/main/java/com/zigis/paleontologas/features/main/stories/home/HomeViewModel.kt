package com.zigis.paleontologas.features.main.stories.home

import com.zigis.paleontologas.core.architecture.v2.BaseViewModel
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.library.stories.periods.PeriodFragment
import com.zigis.paleontologas.features.library.usecases.PeriodListUseCase
import com.zigis.paleontologas.features.main.stories.about.AboutFragment
import com.zigis.paleontologas.features.main.stories.language.LanguageFragment
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressFragment

class HomeViewModel(
    private val globalRouter: GlobalRouter,
    private val periodListUseCase: PeriodListUseCase
) : BaseViewModel<HomeViewState, HomeIntent>() {

    override fun getInitialData() = HomeViewState()

    override suspend fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Initialize -> initialize()
            is HomeIntent.OpenPeriod -> globalRouter.pushFragment(
                PeriodFragment().also {
                    it.periodId = intent.periodId
                }
            )
            is HomeIntent.OpenQuiz -> globalRouter.pushFragment(
                QuizProgressFragment()
            )
            is HomeIntent.OpenLanguages -> globalRouter.pushFragment(
                LanguageFragment()
            )
            is HomeIntent.OpenAbout -> globalRouter.pushFragment(
                AboutFragment()
            )
        }
    }

    private suspend fun initialize() {
        updateState {
            it.copy(
                periodList = periodListUseCase.getPeriodItems(),
                animateLayoutChanges = it.animateLayoutChanges == null
            )
        }
    }
}