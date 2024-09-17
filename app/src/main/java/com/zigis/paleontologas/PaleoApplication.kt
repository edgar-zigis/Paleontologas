package com.zigis.paleontologas

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.zigis.paleontologas.core.providers.AndroidLifecycleProvider
import com.zigis.paleontologas.di.applicationModule
import com.zigis.paleontologas.features.launcher.di.launcherModule
import com.zigis.paleontologas.features.library.di.libraryModule
import com.zigis.paleontologas.features.main.di.mainModule
import com.zigis.paleontologas.features.quiz.di.quizModule
import com.zigis.paleontologas.features.settings.di.settingsModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PaleoApplication : Application() {

    private val eventBusInitializer: EventBusInitializer by inject()
    private val androidLifecycleProvider: AndroidLifecycleProvider by inject()

    override fun onCreate() {
        super.onCreate()
        startActivityMonitor()
        injectDependencies()
        registerActivityLifecycleCallbacks(
            androidLifecycleProvider.activityLifecycleCallbacks
        )
        eventBusInitializer.initialize()
    }

    private fun startActivityMonitor() {
        FirebaseCrashlytics
            .getInstance()
            .setCrashlyticsCollectionEnabled(
                BuildConfig.BUILD_TYPE != "debug"
            )
    }

    private fun injectDependencies() {
        startKoin {
            androidContext(this@PaleoApplication)
            modules(
                listOf(
                    applicationModule,
                    mainModule,
                    quizModule,
                    libraryModule,
                    settingsModule,
                    launcherModule
                )
            )
        }
    }
}