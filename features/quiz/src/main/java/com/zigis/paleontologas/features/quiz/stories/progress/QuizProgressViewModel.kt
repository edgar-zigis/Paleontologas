package com.zigis.paleontologas.features.quiz.stories.progress

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.quiz.routers.QuizRouter
import com.zigis.paleontologas.features.quiz.usecases.QuizProgressUseCase
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressIntent.*

class QuizProgressViewModel(
    private val quizRouter: QuizRouter,
    private val quizProgressUseCase: QuizProgressUseCase
) : BaseViewModel<QuizProgressViewState, QuizProgressIntent>() {

    override fun getInitialData() = QuizProgressViewState()

    override suspend fun handleIntent(intent: QuizProgressIntent) {
        when (intent) {
            is Initialize -> initialize()
            is StartQuiz -> quizRouter.openQuizGame()
            is InvokeBack -> quizRouter.popCurrentScreen()
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