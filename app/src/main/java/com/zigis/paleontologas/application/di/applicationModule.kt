package com.zigis.paleontologas.application.di

import com.zigis.paleontologas.application.managers.ApplicationLocaleManager
import com.zigis.paleontologas.application.managers.ApplicationVersionManager
import com.zigis.paleontologas.launcher.managers.DataMigrationManager
import com.zigis.paleontologas.application.preferences.ApplicationPreferences
import com.zigis.paleontologas.application.routers.GlobalRouter
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val applicationModule = module {
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