package com.zigis.paleontologas.features.library.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

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