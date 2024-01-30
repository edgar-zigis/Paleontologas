package com.zigis.paleontologas.features.main.factories

import com.zigis.paleontologas.features.library.repositories.PeriodRepository
import com.zigis.paleontologas.features.main.stories.home.adapter.HomeListAdapterItem
import com.zigis.paleontologas.features.quiz.repositories.QuestionRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class HomeAdapterItemFactory(
    private val periodRepository: PeriodRepository,
    private val questionRepository: QuestionRepository
) {
    suspend fun getItems(): List<HomeListAdapterItem> {
        val periods = periodRepository.findAll()
        coroutineScope {
            periods.map {
                async {
                    it.quizProgress = calculatePeriodQuizProgress(it.id)
                }
            }.awaitAll()
        }
        return periods.map {
            HomeListAdapterItem(
                id = it.id,
                title = it.title,
                thumbnail = it.thumbnail,
                shortDescription = it.shortDescription,
                timeScale = it.timeScale,
                quizProgress = it.quizProgress
            )
        }
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