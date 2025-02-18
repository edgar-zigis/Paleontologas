package com.zigis.paleontologas.features.library.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Period::class], version = 1, exportSchema = false)
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
            ).fallbackToDestructiveMigration().build()
        }
    }
}