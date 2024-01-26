package com.zigis.paleontologas.features.quiz.stories.game

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.quiz.data.Question
import com.zigis.paleontologas.features.quiz.managers.QuizGameProcessor
import com.zigis.paleontologas.features.quiz.stories.mark.QuizMarkConfiguration
import com.zigis.paleontologas.features.quiz.stories.mark.QuizMarkFragment

class QuizGameViewModel(
    private val globalRouter: GlobalRouter,
    private val quizGameProcessor: QuizGameProcessor
) : BaseViewModel<QuizGameViewState, QuizGameIntent>() {

    override fun getInitialData() = QuizGameViewState()

    override suspend fun handleIntent(intent: QuizGameIntent) {
        when (intent) {
            is QuizGameIntent.Initialize -> initialize()
            is QuizGameIntent.AnswerQuestion -> answerQuestion(
                question = intent.question,
                option = intent.option
            )
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
        val nextQuestion = quizGameProcessor.answerQuestion(question, option)
        if (nextQuestion == null) {
            globalRouter.pushFragment(
                QuizMarkFragment().also {
                    it.configuration = QuizMarkConfiguration(
                        mark = quizGameProcessor.correctAnswers
                    )
                }
            )
        } else {
            updateState {
                it.copy(
                    question = nextQuestion
                )
            }
        }
    }
}