package com.zigis.paleontologas.features.settings.di

import com.zigis.paleontologas.features.settings.factories.AboutListItemFactory
import com.zigis.paleontologas.features.settings.factories.LanguageListItemFactory
import com.zigis.paleontologas.features.settings.routing.SettingsRouter
import com.zigis.paleontologas.features.settings.stories.about.AboutViewModel
import com.zigis.paleontologas.features.settings.stories.language.LanguageViewModel
import com.zigis.paleontologas.features.settings.stories.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    single { SettingsRouter(get()) }

    factory { AboutListItemFactory() }
    factory { LanguageListItemFactory(get()) }

    viewModel { SettingsViewModel(get(), get()) }
    viewModel { AboutViewModel(get(), get(), get()) }
    viewModel { LanguageViewModel(get(), get(), get(), get()) }
}