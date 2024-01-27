package com.zigis.paleontologas.features.main.stories.home

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.library.routers.LibraryRouter
import com.zigis.paleontologas.features.library.usecases.PeriodListUseCase
import com.zigis.paleontologas.features.main.routers.MainRouter
import com.zigis.paleontologas.features.quiz.routers.QuizRouter

class HomeViewModel(
    private val mainRouter: MainRouter,
    private val quizRouter: QuizRouter,
    private val libraryRouter: LibraryRouter,
    private val periodListUseCase: PeriodListUseCase
) : BaseViewModel<HomeViewState, HomeIntent>() {

    override fun getInitialData() = HomeViewState()

    override suspend fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Initialize -> initialize()
            is HomeIntent.OpenPeriod -> libraryRouter.openPeriod(periodId = intent.periodId)
            is HomeIntent.OpenQuiz -> quizRouter.openQuizProgress()
            is HomeIntent.OpenLanguages -> mainRouter.openLanguages()
            is HomeIntent.OpenAbout -> mainRouter.openAboutSection()
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