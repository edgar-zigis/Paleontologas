package com.zigis.paleontologas.features.library.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [LifeForm::class], version = 2, exportSchema = false)
abstract class LifeFormDatabase : RoomDatabase() {

    abstract fun lifeFormDao(): LifeFormDao

    companion object {

        @Volatile
        private var INSTANCE: LifeFormDatabase? = null

        fun getInstance(context: Context): LifeFormDatabase = INSTANCE
            ?: synchronized(this) {
                INSTANCE
                    ?: createDatabase(
                        context
                    ).also {
                        INSTANCE = it
                    }
            }

        private fun createDatabase(context: Context): LifeFormDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                LifeFormDatabase::class.java,
                "life_forms"
            )
                .addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration(true)
                .build()
        }

        //  Migrations

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE life_forms ADD COLUMN orderIndex INTEGER NOT NULL DEFAULT 0"
                )
            }
        }
    }
}