package com.zigis.paleontologas.features.library.factories

import com.zigis.paleontologas.features.library.repositories.LifeFormRepository
import com.zigis.paleontologas.features.library.stories.geologicalperiod.list.GeologicalPeriodListItem

class GeologicalPeriodListItemFactory(
    private val lifeFormRepository: LifeFormRepository
) {
    suspend fun getItems(periodId: Int): List<GeologicalPeriodListItem> {
        return lifeFormRepository.findAll(periodId).sortedBy {
            it.order
        }.map {
            GeologicalPeriodListItem(
                id = it.id,
                title = it.title,
                thumbnail = it.thumbnail
            )
        }
    }
}