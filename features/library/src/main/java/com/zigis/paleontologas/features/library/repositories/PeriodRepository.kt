package com.zigis.paleontologas.features.library.repositories

import androidx.annotation.WorkerThread
import com.zigis.paleontologas.core.architecture.BaseRepository
import com.zigis.paleontologas.features.library.data.PeriodDao
import com.zigis.paleontologas.features.library.data.Period
import com.zigis.paleontologas.features.library.data.stores.PeriodDataStore

class PeriodRepository(
    private val periodDao: PeriodDao,
    private val periodDataStore: PeriodDataStore
) : BaseRepository<Period>(periodDao, tableName = "periods") {

    @WorkerThread
    override suspend fun initialize() {
        val list = mutableListOf<Period>()
        list.addAll(
            periodDataStore.getPeriods().map {
                Period(
                    id = it.id,
                    title = it.titleSlug,
                    additionalTitle = it.additionalTitleSlug,
                    shortDescription = it.shortDescriptionSlug,
                    description = it.descriptionSlug,
                    additionalDescription = it.additionalDescriptionSlug,
                    timeScale = it.timeScale,
                    environmentDescription = it.environmentSlug,
                    lifeFormDescription = it.lifeFormDescriptionSlug,
                    thumbnail = it.thumbnailId,
                    artwork = it.artworkId,
                    artworkAuthor = it.artworkAuthor ?: "",
                    map = it.mapId ?: "",
                    mapAuthor = it.mapAuthor ?: ""
                )
            }
        )
        deleteAll()
        periodDao.insertAll(list)
    }
}