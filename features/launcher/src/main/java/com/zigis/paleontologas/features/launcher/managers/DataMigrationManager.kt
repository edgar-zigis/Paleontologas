package com.zigis.paleontologas.features.launcher.managers

import com.zigis.paleontologas.core.entities.ApplicationVersion
import com.zigis.paleontologas.core.managers.ApplicationVersionManager
import com.zigis.paleontologas.core.preferences.ApplicationPreferences
import com.zigis.paleontologas.features.library.repositories.LifeFormRepository
import com.zigis.paleontologas.features.library.repositories.PeriodRepository
import com.zigis.paleontologas.features.quiz.repositories.QuestionRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DataMigrationManager(
    private val applicationPreferences: ApplicationPreferences,
    private val applicationVersionManager: ApplicationVersionManager,
    private val periodRepository: PeriodRepository,
    private val lifeFormRepository: LifeFormRepository,
    private val questionRepository: QuestionRepository
) {
    private val mutex = Mutex()

    suspend fun migrate() = mutex.withLock {
        val previousApplicationVersion = applicationPreferences.version
        val currentApplicationVersion = applicationVersionManager.getApplicationVersionName()

        if (periodRepository.findAll().isEmpty()) {
            updateRepositories(currentApplicationVersion)
        }

        if (previousApplicationVersion == null) {
            return
        }

        val previousVersion = ApplicationVersion(previousApplicationVersion)
        val currentVersion = ApplicationVersion(currentApplicationVersion)

        if (previousVersion != currentVersion) {
            updateRepositories(currentVersion.version)
        }
    }

    private suspend fun updateRepositories(newVersion: String) {
        periodRepository.initialize()
        lifeFormRepository.initialize()
        questionRepository.initialize()

        applicationPreferences.version = newVersion
    }
}