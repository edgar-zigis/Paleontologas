package com.zigis.paleontologas.features.quiz.stories.progress

import com.zigis.paleontologas.core.architecture.v2.BaseViewModel
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.quiz.stories.game.QuizGameFragment
import com.zigis.paleontologas.features.quiz.usecases.QuizProgressUseCase

class QuizProgressViewModel(
    private val globalRouter: GlobalRouter,
    private val quizProgressUseCase: QuizProgressUseCase
) : BaseViewModel<QuizProgressViewState, QuizProgressIntent>() {

    override fun getInitialData() = QuizProgressViewState()

    override suspend fun handleIntent(intent: QuizProgressIntent) {
        when (intent) {
            is QuizProgressIntent.Initialize -> initialize()
            is QuizProgressIntent.StartQuiz -> globalRouter.pushFragment(
                QuizGameFragment()
            )
        }
    }

    private suspend fun initialize() {
        updateState {
            it.copy(
                progress = quizProgressUseCase.getFullProgress()
            )
        }
    }
}