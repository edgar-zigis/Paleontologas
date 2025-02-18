package com.zigis.paleontologas.core.architecture

import androidx.sqlite.db.SimpleSQLiteQuery

abstract class BaseRepository<T>(
    private val dao: BaseDao<T>,
    private val tableName: String
) {
    abstract suspend fun initialize()

    suspend fun findOne(id: Int): T {
        return dao.findOne(
            SimpleSQLiteQuery(
                "SELECT * FROM $tableName  WHERE id=$id"
            )
        )
    }

    suspend fun findAll(): List<T> {
        return dao.findAll(
            SimpleSQLiteQuery(
                "SELECT * FROM $tableName"
            )
        )
    }

    suspend fun findAll(query: SimpleSQLiteQuery): List<T> {
        return dao.findAll(query)
    }

    protected suspend fun deleteAll(): Int {
        return dao.deleteAll(
            SimpleSQLiteQuery(
                "DELETE FROM $tableName"
            )
        )
    }
}