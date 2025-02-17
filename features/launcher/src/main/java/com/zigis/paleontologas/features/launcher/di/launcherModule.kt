package com.zigis.paleontologas.features.launcher.di

import com.zigis.paleontologas.features.launcher.managers.DataMigrationManager
import com.zigis.paleontologas.features.launcher.story.LauncherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val launcherModule = module {

    single { DataMigrationManager(get(), get(), get(), get(), get()) }

    viewModel { LauncherViewModel(get(), get()) }
}