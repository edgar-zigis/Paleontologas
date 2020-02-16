package com.zigis.paleontologas.quiz.usecases

import com.zigis.paleontologas.quiz.data.entities.Question
import com.zigis.paleontologas.quiz.repositories.QuestionRepository

class QuizGenerateQuestionsUseCase(
    private val questionRepository: QuestionRepository
) {
    suspend fun generateRandomQuestions(questionCount: Int): List<Question> {
        val questions = questionRepository.findAll().shuffled()
        return processQuestions(questions.take(questionCount))
    }

    private fun processQuestions(questions: List<Question>): List<Question> {
        questions.forEach {
            it.variantList.addAll(
                listOf(
                    it.periodName + "_variant_" + it.questionIndex + "_1",
                    it.periodName + "_variant_" + it.questionIndex + "_2",
                    it.periodName + "_variant_" + it.questionIndex + "_3",
                    it.periodName + "_variant_" + it.questionIndex + "_4"
                )
            )
            it.variantList.shuffle()
        }
        return questions
    }
}