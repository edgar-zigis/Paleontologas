package com.zigis.paleontologas.features.main.stories.home

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.library.stories.periods.PeriodConfiguration
import com.zigis.paleontologas.features.library.stories.periods.PeriodFragment
import com.zigis.paleontologas.features.library.usecases.PeriodListUseCase
import com.zigis.paleontologas.features.main.stories.about.AboutFragment
import com.zigis.paleontologas.features.main.stories.language.LanguageFragment
import com.zigis.paleontologas.features.quiz.routers.QuizRouter

class HomeViewModel(
    private val quizRouter: QuizRouter,
    private val globalRouter: GlobalRouter,
    private val periodListUseCase: PeriodListUseCase
) : BaseViewModel<HomeViewState, HomeIntent>() {

    override fun getInitialData() = HomeViewState()

    override suspend fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Initialize -> initialize()
            is HomeIntent.OpenPeriod -> globalRouter.pushFragment(
                PeriodFragment().also {
                    it.configuration = PeriodConfiguration(
                        periodId = intent.periodId
                    )
                }
            )
            is HomeIntent.OpenQuiz -> quizRouter.openQuizProgress()
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