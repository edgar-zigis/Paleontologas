package com.zigis.paleontologas.features.quiz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Question::class], version = 1)
abstract class QuestionDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao

    companion object {

        @Volatile
        private var INSTANCE: QuestionDatabase? = null

        fun getInstance(context: Context): QuestionDatabase = INSTANCE
            ?: synchronized(this) {
                INSTANCE
                    ?: createDatabase(
                        context
                    ).also {
                        INSTANCE = it
                    }
            }

        private fun createDatabase(context: Context): QuestionDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                QuestionDatabase::class.java,
                "question"
            ).fallbackToDestructiveMigration().build()
        }
    }
}