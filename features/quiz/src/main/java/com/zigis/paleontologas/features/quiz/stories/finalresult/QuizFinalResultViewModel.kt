package com.zigis.paleontologas.features.quiz.stories.finalresult

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.quiz.managers.QuizGameProcessor
import com.zigis.paleontologas.features.quiz.routing.QuizRouter
import com.zigis.paleontologas.features.quiz.stories.finalresult.QuizFinalResultIntent.*

class QuizFinalResultViewModel(
    private val quizRouter: QuizRouter
) : BaseViewModel<QuizFinalResultViewState, QuizFinalResultIntent>() {

    override fun getInitialData() = QuizFinalResultViewState()

    override suspend fun handleIntent(intent: QuizFinalResultIntent) {
        when (intent) {
            is Initialize -> initialize(mark = intent.mark)
            is InvokeBack -> quizRouter.popCurrentScreen(
                isImmediate = true,
                repeatCount = 2
            )
        }
    }

    private suspend fun initialize(mark: Int) {
        updateState {
            it.copy(
                mark = mark,
                totalQuestions = QuizGameProcessor.questionsToGenerateCount
            )
        }
    }
}