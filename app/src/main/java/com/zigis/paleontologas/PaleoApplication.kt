package com.zigis.paleontologas

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.zigis.paleontologas.core.providers.AndroidLifecycleProvider
import com.zigis.paleontologas.di.applicationModule
import com.zigis.paleontologas.di.dataModule
import com.zigis.paleontologas.di.preferenceModule
import com.zigis.paleontologas.di.quizModule
import com.zigis.paleontologas.di.useCaseModule
import com.zigis.paleontologas.di.viewModelModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PaleoApplication : Application() {

    companion object {
        const val disableCrashLytics = false
    }

    private val androidLifecycleProvider: AndroidLifecycleProvider by inject()

    override fun onCreate() {
        super.onCreate()
        startActivityMonitor()
        injectDependencies()
        registerActivityLifecycleCallbacks(androidLifecycleProvider.activityLifecycleCallbacks)
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