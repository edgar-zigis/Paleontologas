package com.zigis.paleontologas.features.quiz.managers

import com.zigis.paleontologas.features.quiz.data.Question
import com.zigis.paleontologas.features.quiz.repositories.QuestionRepository
import com.zigis.paleontologas.features.quiz.usecases.QuizGenerateQuestionsUseCase

class QuizGameProcessor constructor(
    private val questionRepository: QuestionRepository,
    private val quizGenerateQuestionsUseCase: QuizGenerateQuestionsUseCase
) {
    companion object {
        const val questionsToGenerateCount = 10
    }

    var correctAnswers = 0
        private set

    private var generatedQuestions = listOf<Question>()

    suspend fun generateRandomQuestions(): List<Question> {
        correctAnswers = 0
        generatedQuestions = quizGenerateQuestionsUseCase.generateRandomQuestions(
            questionsToGenerateCount
        )
        return generatedQuestions
    }

    suspend fun answerQuestion(question: Question, variantIndex: Int): Question? {
        if (question.getCorrectVariantIndex() == variantIndex) {
            question.isAnswered = true
            correctAnswers += 1
        }
        val nextQuestionIndex = generatedQuestions.indexOf(question) + 1
        return if (nextQuestionIndex < generatedQuestions.size) {
            generatedQuestions[nextQuestionIndex]
        } else {
            generatedQuestions.map { questionRepository.update(it) }
            null
        }
    }
}