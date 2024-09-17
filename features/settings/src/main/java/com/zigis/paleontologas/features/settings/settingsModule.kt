package com.zigis.paleontologas.features.settings

import com.zigis.paleontologas.features.settings.factories.AboutListItemFactory
import com.zigis.paleontologas.features.settings.factories.LanguageListItemFactory
import com.zigis.paleontologas.features.settings.routing.SettingsRouter
import com.zigis.paleontologas.features.settings.stories.about.AboutViewModel
import com.zigis.paleontologas.features.settings.stories.language.LanguageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    single { SettingsRouter(get()) }

    factory { AboutListItemFactory() }
    factory { LanguageListItemFactory(get()) }

    viewModel { AboutViewModel(get(), get(), get()) }
    viewModel { LanguageViewModel(get(), get(), get()) }
}