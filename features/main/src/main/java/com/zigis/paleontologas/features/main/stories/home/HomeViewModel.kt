package com.zigis.paleontologas.features.main.stories.home

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.library.routers.LibraryRouter
import com.zigis.paleontologas.features.main.factories.HomeAdapterItemFactory
import com.zigis.paleontologas.features.main.routers.MainRouter
import com.zigis.paleontologas.features.quiz.routers.QuizRouter
import com.zigis.paleontologas.features.main.stories.home.HomeIntent.*

class HomeViewModel(
    private val mainRouter: MainRouter,
    private val quizRouter: QuizRouter,
    private val libraryRouter: LibraryRouter,
    private val homeAdapterItemFactory: HomeAdapterItemFactory
) : BaseViewModel<HomeViewState, HomeIntent>() {

    override fun getInitialData() = HomeViewState()

    override suspend fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is Initialize -> initialize()
            is OpenPeriod -> libraryRouter.openPeriod(periodId = intent.periodId)
            is OpenQuiz -> quizRouter.openQuizProgress()
            is OpenLanguages -> mainRouter.openLanguages()
            is OpenAbout -> mainRouter.openAboutSection()
        }
    }

    private suspend fun initialize() {
        updateState {
            it.copy(
                periodItems = homeAdapterItemFactory.getItems(),
                animateLayoutChanges = it.animateLayoutChanges == null
            )
        }
    }
}