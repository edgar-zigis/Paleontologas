package com.zigis.paleontologas.di

import com.zigis.paleontologas.core.managers.ApplicationLocaleManager
import com.zigis.paleontologas.core.managers.ApplicationVersionManager
import com.zigis.paleontologas.features.launcher.managers.DataMigrationManager
import com.zigis.paleontologas.core.preferences.ApplicationPreferences
import com.zigis.paleontologas.core.providers.AndroidLifecycleProvider
import com.zigis.paleontologas.core.routers.GlobalRouter
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val applicationModule = module {
    single { AndroidLifecycleProvider(androidContext()) }
    single { ApplicationPreferences(get(named("applicationPreferences"))) }
    single { ApplicationVersionManager(androidContext()) }
    single { ApplicationLocaleManager(get()) }

    single {
        DataMigrationManager(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    single { GlobalRouter() }
}