package com.zigis.paleontologas.features.quiz.data

import androidx.room.Dao
import androidx.room.Update
import com.zigis.paleontologas.core.architecture.BaseDao

@Dao
interface QuestionDao : BaseDao<Question> {
    @Update
    suspend fun update(vararg question: Question)
}