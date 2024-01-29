package com.zigis.paleontologas.features.quiz.stories.finalresult

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.quiz.managers.QuizGameProcessor
import com.zigis.paleontologas.features.quiz.stories.finalresult.QuizFinalResultIntent.*

class QuizFinalResultViewModel : BaseViewModel<QuizFinalResultkViewState, QuizFinalResultIntent>() {

    override fun getInitialData() = QuizFinalResultkViewState()

    override suspend fun handleIntent(intent: QuizFinalResultIntent) {
        when (intent) {
            is Initialize -> initialize(mark = intent.mark)
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