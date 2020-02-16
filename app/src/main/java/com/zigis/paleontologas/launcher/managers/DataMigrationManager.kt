package com.zigis.paleontologas.launcher.managers

import com.zigis.paleontologas.application.entities.ApplicationVersion
import com.zigis.paleontologas.application.managers.ApplicationVersionManager
import com.zigis.paleontologas.application.preferences.ApplicationPreferences
import com.zigis.paleontologas.periods.repositories.LifeFormRepository
import com.zigis.paleontologas.periods.repositories.PeriodRepository
import com.zigis.paleontologas.quiz.repositories.QuestionRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DataMigrationManager constructor(
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