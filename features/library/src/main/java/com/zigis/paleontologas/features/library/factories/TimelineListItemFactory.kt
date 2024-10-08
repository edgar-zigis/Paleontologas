package com.zigis.paleontologas.features.library.factories

import com.zigis.paleontologas.features.library.repositories.PeriodRepository
import com.zigis.paleontologas.features.library.stories.timeline.list.TimelineListItem
import com.zigis.paleontologas.features.quiz.repositories.QuestionRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class TimelineListItemFactory(
    private val periodRepository: PeriodRepository,
    private val questionRepository: QuestionRepository
) {
    suspend fun getItems(): List<TimelineListItem> {
        val periods = periodRepository.findAll()
        coroutineScope {
            periods.map {
                async {
                    it.quizProgress = calculatePeriodQuizProgress(it.id)
                }
            }.awaitAll()
        }
        return periods.map {
            TimelineListItem(
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