package com.zigis.paleontologas.features.library.repositories

import androidx.annotation.WorkerThread
import androidx.sqlite.db.SimpleSQLiteQuery
import com.zigis.paleontologas.core.architecture.BaseRepository
import com.zigis.paleontologas.features.library.data.LifeFormDao
import com.zigis.paleontologas.features.library.data.LifeForm
import com.zigis.paleontologas.features.library.data.stores.LifeFormDataStore

class LifeFormRepository(
    private val lifeFormDao: LifeFormDao,
    private val lifeFormDataStore: LifeFormDataStore
) : BaseRepository<LifeForm>(lifeFormDao, tableName = "life_forms") {

    @WorkerThread
    override suspend fun initialize() {
        val list = mutableListOf<LifeForm>()
        for (i in 2..15) {
            list.addAll(
                lifeFormDataStore.getLifeForms(i).map {
                    LifeForm(
                        id = it.id,
                        periodId = i,
                        title = it.titleSlug,
                        description = it.descriptionSlug,
                        timeScale = it.timeScale,
                        thumbnail = it.thumbnailId,
                        artwork = it.artworkId,
                        additionalArtwork = it.additionalArtworkId ?: "",
                        artworkAuthor = it.artworkAuthor ?: "",
                        additionalArtworkAuthor = it.additionalArtworkAuthor ?: "",
                        order = it.order
                    )
                }
            )
        }
        deleteAll()
        lifeFormDao.insertAll(list)
    }

    suspend fun findAll(periodId: Int): List<LifeForm> {
        val query = SimpleSQLiteQuery("SELECT * FROM life_forms WHERE periodId=${periodId}")
        return findAll(query)
    }
}