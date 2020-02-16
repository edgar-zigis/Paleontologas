package com.zigis.paleontologas.periods.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zigis.paleontologas.periods.data.daos.LifeFormDao
import com.zigis.paleontologas.periods.data.entities.LifeForm

@Database(entities = [LifeForm::class], version = 1)
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
                "lifeform"
            ).fallbackToDestructiveMigration().build()
        }
    }
}