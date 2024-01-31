package com.zigis.paleontologas

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.zigis.paleontologas.core.providers.AndroidLifecycleProvider
import com.zigis.paleontologas.features.launcher.launcherModule
import com.zigis.paleontologas.features.library.libraryModule
import com.zigis.paleontologas.features.main.mainModule
import com.zigis.paleontologas.features.quiz.quizModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PaleoApplication : Application() {

    private val androidLifecycleProvider: AndroidLifecycleProvider by inject()

    override fun onCreate() {
        super.onCreate()
        startActivityMonitor()
        injectDependencies()
        registerActivityLifecycleCallbacks(
            androidLifecycleProvider.activityLifecycleCallbacks
        )
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
                    launcherModule
                )
            )
        }
    }
}