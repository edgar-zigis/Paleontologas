package com.zigis.paleontologas.features.library.usecases

import com.zigis.paleontologas.features.library.data.Period
import com.zigis.paleontologas.features.library.repositories.PeriodRepository
import com.zigis.paleontologas.features.quiz.repositories.QuestionRepository

class PeriodListUseCase(
    private val periodRepository: PeriodRepository,
    private val questionRepository: QuestionRepository
) {
    suspend fun getPeriodItems(): List<Period> {
        val periods = periodRepository.findAll()
        periods.forEach { period ->
            period.quizProgress = calculatePeriodQuizProgress(period.id)
        }
        return periods
    }

    private suspend fun calculatePeriodQuizProgress(periodId: Int): Int {
        var progress = 0f
        val questions = questionRepository.findAll(periodId)
        questions.forEach { question ->
            if (question.isAnswered) progress += 1
        }
        return (progress / questions.size * 100).toInt()
    }
}