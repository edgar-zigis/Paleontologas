package com.zigis.paleontologas.features.quiz.repositories

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.sqlite.db.SimpleSQLiteQuery
import com.zigis.paleontologas.core.architecture.BaseRepository
import com.zigis.paleontologas.core.extensions.getString
import com.zigis.paleontologas.features.quiz.data.Question
import com.zigis.paleontologas.features.quiz.data.QuestionDao
import com.zigis.paleontologas.features.quiz.data.stores.QuizQuestionDataStore

class QuestionRepository(
    private val context: Context,
    private val questionDao: QuestionDao,
    private val questionDataStore: QuizQuestionDataStore
) : BaseRepository<Question>(questionDao, "quiz_questions") {

    @WorkerThread
    override suspend fun initialize() {
        val list = mutableListOf<Question>()
        var questionId = 0
        (1..16).map { periodId ->
            questionDataStore.getQuestionDefinitions(periodId).forEach {
                questionId++
                list.add(
                    Question(
                        id = questionId,
                        periodId = periodId,
                        periodName = it.periodName,
                        questionIndex = it.index,
                        artwork = context.getString("${it.periodName}_question_${it.index}_image"),
                        category = getQuestionCategory(it).value,
                        isAnswered = false
                    )
                )
            }
        }
        migratePreviousQuestions(newQuestions = list)
        deleteAll()
        questionDao.insertAll(list)
    }

    suspend fun findAll(periodId: Int): List<Question> {
        val query = SimpleSQLiteQuery("SELECT * FROM quiz_questions WHERE periodId=${periodId}")
        return findAll(query)
    }

    suspend fun update(question: Question) {
        questionDao.update(question)
    }

    private suspend fun migratePreviousQuestions(newQuestions: List<Question>) {
        val previousQuestions = findAll()
        previousQuestions.forEach { previousQuestion ->
            newQuestions.forEach { newQuestion ->
                if (
                    newQuestion.periodId == previousQuestion.periodId
                    && newQuestion.questionIndex == previousQuestion.questionIndex
                ) {
                    newQuestion.isAnswered = previousQuestion.isAnswered
                }
            }
        }
    }

    private fun getQuestionCategory(question: QuizQuestionDataStore.DTO): Question.Category {
        val assignedType = "${question.periodName}_question_${question.index}_type"
        if (context.getString(assignedType) == assignedType) {
            return question.category
        }
        return Question.Category.fromString(context.getString(assignedType))
    }
}