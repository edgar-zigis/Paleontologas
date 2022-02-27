package com.zigis.paleontologas

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.zigis.paleontologas.application.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PaleoApplication : Application() {

    companion object {
        const val disableCrashLytics = false
    }

    override fun onCreate() {
        super.onCreate()
        //startActivityMonitor()
        injectDependencies()
    }

    private fun startActivityMonitor() {
        if (disableCrashLytics) {
            return
        }
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
                    preferenceModule,
                    dataModule,
                    quizModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}