package com.zigis.paleontologas.features.quiz.usecases

import com.zigis.paleontologas.features.quiz.repositories.QuestionRepository

class QuizProgressUseCase(
    private val questionRepository: QuestionRepository
) {
    suspend fun getFullProgress(): Float {
        val allQuestions = questionRepository.findAll()
        val answeredQuestions = allQuestions.filter { it.isAnswered }

        return (answeredQuestions.size.toFloat() / allQuestions.size.toFloat()) * 100f
    }
}