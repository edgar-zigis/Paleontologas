package com.zigis.paleontologas.features.quiz.stories.game

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.quiz.data.Question
import com.zigis.paleontologas.features.quiz.managers.QuizGameProcessor
import com.zigis.paleontologas.features.quiz.routing.QuizRouter
import com.zigis.paleontologas.features.quiz.stories.game.QuizGameIntent.*
import kotlinx.coroutines.delay

class QuizGameViewModel(
    private val quizRouter: QuizRouter,
    private val quizGameProcessor: QuizGameProcessor
) : BaseViewModel<QuizGameViewState, QuizGameIntent>() {

    override fun getInitialData() = QuizGameViewState()

    override suspend fun handleIntent(intent: QuizGameIntent) {
        when (intent) {
            is Initialize -> initialize()
            is AnswerQuestion -> answerQuestion(
                question = intent.question,
                option = intent.option
            )
            is InvokeBack -> quizRouter.popCurrentScreen()
        }
    }

    private suspend fun initialize() {
        updateState {
            it.copy(
                question = quizGameProcessor.generateRandomQuestions().first()
            )
        }
    }

    private suspend fun answerQuestion(question: Question, option: Int) {
        updateState {
            it.copy(
                chosenOption = option,
                correctOption = question.getCorrectVariantIndex(),
                isInTransition = false
            )
        }
        delay(1500L)
        updateState {
            it.copy(
                isInTransition = true
            )
        }
        delay(250L)
        val nextQuestion = quizGameProcessor.answerQuestion(question, option)
        if (nextQuestion == null) {
            quizRouter.openQuizFinalResultPreview(mark = quizGameProcessor.correctAnswers)
        } else {
            updateState {
                it.copy(
                    question = nextQuestion,
                    chosenOption = null,
                    correctOption = null,
                    isInTransition = false
                )
            }
        }
    }
}