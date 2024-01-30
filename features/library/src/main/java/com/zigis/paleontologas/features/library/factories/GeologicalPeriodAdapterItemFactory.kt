package com.zigis.paleontologas.features.library.factories

import com.zigis.paleontologas.features.library.repositories.LifeFormRepository
import com.zigis.paleontologas.features.library.stories.geologicalperiod.adapter.GeologicalPeriodListAdapterItem

class GeologicalPeriodAdapterItemFactory(
    private val lifeFormRepository: LifeFormRepository
) {
    suspend fun getItems(periodId: Int): List<GeologicalPeriodListAdapterItem> {
        return lifeFormRepository.findAll(periodId).map {
            GeologicalPeriodListAdapterItem(
                id = it.id,
                title = it.title,
                thumbnail = it.thumbnail
            )
        }
    }
}