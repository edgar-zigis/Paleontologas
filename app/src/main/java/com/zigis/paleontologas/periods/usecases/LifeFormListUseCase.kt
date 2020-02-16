package com.zigis.paleontologas.periods.usecases

import com.zigis.paleontologas.periods.data.entities.LifeForm
import com.zigis.paleontologas.periods.repositories.LifeFormRepository

class LifeFormListUseCase(
    private val lifeFormRepository: LifeFormRepository
) {
    suspend fun getPeriodItems(periodId: Int): List<LifeForm> {
        return lifeFormRepository.findAll(periodId)
    }
}