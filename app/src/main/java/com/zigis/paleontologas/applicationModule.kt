package com.zigis.paleontologas

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.zigis.paleontologas.core.managers.ApplicationLocaleManager
import com.zigis.paleontologas.core.managers.ApplicationVersionManager
import com.zigis.paleontologas.core.preferences.ApplicationPreferences
import com.zigis.paleontologas.core.providers.AndroidLifecycleProvider
import com.zigis.paleontologas.core.routers.GlobalRouter
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val applicationModule = module {
    single { AndroidLifecycleProvider(androidContext()) }
    single { ApplicationPreferences(get(named("applicationPreferences"))) }
    single { ApplicationVersionManager(androidContext()) }
    single { ApplicationLocaleManager(get()) }

    single { GlobalRouter() }

    single(named("applicationPreferences")) {
        provideApplicationSharedPreferences(androidApplication())
    }
}

private fun provideApplicationSharedPreferences(application: Application): SharedPreferences {
    return application.getSharedPreferences("applicationPreferences", Context.MODE_PRIVATE)
}