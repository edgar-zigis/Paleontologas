package com.zigis.paleontologas.features.quiz.repositories

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.sqlite.db.SimpleSQLiteQuery
import com.zigis.paleontologas.core.architecture.BaseRepository
import com.zigis.paleontologas.features.quiz.R
import com.zigis.paleontologas.features.quiz.data.Question
import com.zigis.paleontologas.features.quiz.data.QuestionDao

class QuestionRepository(
    private val context: Context,
    private val questionDao: QuestionDao
) : BaseRepository<Question>(questionDao) {

    @WorkerThread
    override suspend fun initialize() {
        val list = mutableListOf<Question>()
        with(list) {
            insertQuestions(this, 1, "hadean", R.array.hadean_questions)
            insertQuestions(this, 2, "archean", R.array.archean_questions)
            insertQuestions(this, 3, "proterozoic", R.array.proterozoic_questions)
            insertQuestions(this, 4, "cambrian", R.array.cambrian_questions)
            insertQuestions(this, 5, "ordovician", R.array.ordovician_questions)
            insertQuestions(this, 6, "silurian", R.array.silurian_questions)
            insertQuestions(this, 7, "devonian", R.array.devonian_questions)
            insertQuestions(this, 8, "carboniferous", R.array.carboniferous_questions)
            insertQuestions(this, 9, "permian", R.array.permian_questions)
            insertQuestions(this, 10, "triassic", R.array.triassic_questions)
            insertQuestions(this, 11, "jurassic", R.array.jurassic_questions)
            insertQuestions(this, 12, "cretaceous", R.array.cretaceous_questions)
            insertQuestions(this, 13, "paleogene", R.array.paleogene_questions)
            insertQuestions(this, 14, "neogene", R.array.neogene_questions)
            insertQuestions(this, 15, "quaternary", R.array.quaternary_questions)
            insertQuestions(this, 16, "future", R.array.future_questions)
            migratePreviousQuestions(newQuestions = this)
        }
        deleteAll()
        questionDao.insertAll(list)
    }

    suspend fun findAll(periodId: Int): List<Question> {
        val query = SimpleSQLiteQuery("SELECT * FROM question WHERE periodId=${periodId}")
        return findAll(query)
    }

    suspend fun update(question: Question) {
        questionDao.update(question)
    }

    private fun insertQuestions(
        list: MutableList<Question>,
        periodId: Int,
        periodName: String,
        artworkArrayId: Int
    ) {
        val artworks = context.resources.getStringArray(artworkArrayId)
        artworks.forEachIndexed { index, artwork ->
            list.add(
                Question(
                    id = list.size + 1,
                    periodId = periodId,
                    periodName = periodName,
                    questionIndex = index + 1,
                    artwork = artwork,
                    isAnswered = false
                )
            )
        }
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
}