package com.zigis.paleontologas.features.quiz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Question::class], version = 2, exportSchema = false)
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
                "quiz_questions"
            )
                .addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration(true).build()
        }

        //  Migrations

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE quiz_questions ADD COLUMN category TEXT NOT NULL DEFAULT ''"
                )
            }
        }
    }
}