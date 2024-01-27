package com.zigis.paleontologas

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.zigis.paleontologas.core.managers.ApplicationLocaleManager
import com.zigis.paleontologas.core.managers.ApplicationVersionManager
import com.zigis.paleontologas.features.launcher.managers.DataMigrationManager
import com.zigis.paleontologas.core.preferences.ApplicationPreferences
import com.zigis.paleontologas.core.providers.AndroidLifecycleProvider
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.launcher.stories.main.LauncherViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val applicationModule = module {
    single { AndroidLifecycleProvider(androidContext()) }
    single { ApplicationPreferences(get(named("applicationPreferences"))) }
    single { ApplicationVersionManager(androidContext()) }
    single { ApplicationLocaleManager(get()) }

    single { DataMigrationManager(get(), get(), get(), get(), get()) }
    single { GlobalRouter() }

    single(named("applicationPreferences")) {
        provideApplicationSharedPreferences(androidApplication())
    }

    viewModel { LauncherViewModel(get(), get()) }
}

private fun provideApplicationSharedPreferences(application: Application): SharedPreferences {
    return application.getSharedPreferences("applicationPreferences", Context.MODE_PRIVATE)
}