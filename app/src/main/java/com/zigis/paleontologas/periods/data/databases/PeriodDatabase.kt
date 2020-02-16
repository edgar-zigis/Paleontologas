package com.zigis.paleontologas.periods.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zigis.paleontologas.periods.data.daos.PeriodDao
import com.zigis.paleontologas.periods.data.entities.Period

@Database(entities = [Period::class], version = 1)
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
                "period"
            ).fallbackToDestructiveMigration().build()
        }
    }
}