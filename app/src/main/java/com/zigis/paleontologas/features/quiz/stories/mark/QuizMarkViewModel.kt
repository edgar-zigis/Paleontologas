package com.zigis.paleontologas.features.quiz.stories.mark

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.quiz.managers.QuizGameProcessor

class QuizMarkViewModel : BaseViewModel<QuizMarkViewState, QuizMarkIntent>() {

    override fun getInitialData() = QuizMarkViewState()

    override suspend fun handleIntent(intent: QuizMarkIntent) {
        when (intent) {
            is QuizMarkIntent.Initialize -> initialize(mark = intent.mark)
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