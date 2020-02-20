package com.zigis.paleontologas

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.zigis.paleontologas.application.di.*
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PaleoApplication : Application() {

    companion object {
        const val disableCrashLytics = true
    }

    override fun onCreate() {
        super.onCreate()
        startActivityMonitor()
        injectDependencies()
    }

    private fun startActivityMonitor() {
        if (disableCrashLytics) return
        val builder = Crashlytics.Builder()
            .core(CrashlyticsCore.Builder().disabled(BuildConfig.BUILD_TYPE == "debug").build())
            .build()
        Fabric.with(this, builder)
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