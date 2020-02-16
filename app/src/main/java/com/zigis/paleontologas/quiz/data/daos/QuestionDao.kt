package com.zigis.paleontologas.quiz.data.daos

import androidx.room.Dao
import androidx.room.Update
import com.zigis.paleontologas.application.data.BaseDao
import com.zigis.paleontologas.quiz.data.entities.Question

@Dao
interface QuestionDao : BaseDao<Question> {
    @Update
    suspend fun update(vararg question: Question)
}