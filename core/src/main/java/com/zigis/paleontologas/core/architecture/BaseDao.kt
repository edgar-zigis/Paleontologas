package com.zigis.paleontologas.core.architecture

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<T>)

    @RawQuery
    suspend fun findOne(query: SupportSQLiteQuery): T

    @RawQuery
    suspend fun findAll(query: SupportSQLiteQuery): List<T>

    @RawQuery
    suspend fun deleteAll(query: SupportSQLiteQuery): Int
}