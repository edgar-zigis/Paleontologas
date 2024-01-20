package com.zigis.paleontologas.features.library.usecases

import com.zigis.paleontologas.features.library.data.LifeForm
import com.zigis.paleontologas.features.library.repositories.LifeFormRepository

class LifeFormListUseCase(
    private val lifeFormRepository: LifeFormRepository
) {
    suspend fun getPeriodItems(periodId: Int): List<LifeForm> {
        return lifeFormRepository.findAll(periodId)
    }
}