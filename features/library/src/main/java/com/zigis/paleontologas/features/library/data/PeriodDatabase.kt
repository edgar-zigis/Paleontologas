package com.zigis.paleontologas.features.library.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Period::class], version = 2, exportSchema = false)
abstract class PeriodDatabase : RoomDatabase() {

    abstract fun periodDao(): PeriodDao

    companion object {

        @Volatile
        private var INSTANCE: PeriodDatabase? = null

        fun getInstance(context: Context): PeriodDatabase = INSTANCE
            ?: synchronized(this) {
                INSTANCE
                    ?: createDatabase(
                        context
                    ).also {
                        INSTANCE = it
                    }
            }

        private fun createDatabase(context: Context): PeriodDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                PeriodDatabase::class.java,
                "periods"
            )
                .addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigration(true).build()
        }

        //  Migrations

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE periods ADD COLUMN mapAuthor TEXT NOT NULL DEFAULT ''"
                )
            }
        }
    }
}