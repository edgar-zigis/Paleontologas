package com.zigis.paleontologas.features.library.factories

import com.zigis.paleontologas.features.library.repositories.LifeFormRepository
import com.zigis.paleontologas.features.library.stories.geologicalperiod.list.GeologicalPeriodListItem

class GeologicalPeriodAdapterItemFactory(
    private val lifeFormRepository: LifeFormRepository
) {
    suspend fun getItems(periodId: Int): List<GeologicalPeriodListItem> {
        return lifeFormRepository.findAll(periodId).map {
            GeologicalPeriodListItem(
                id = it.id,
                title = it.title,
                thumbnail = it.thumbnail
            )
        }
    }
}