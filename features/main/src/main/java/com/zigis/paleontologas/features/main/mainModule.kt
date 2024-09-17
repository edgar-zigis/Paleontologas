package com.zigis.paleontologas.features.main

import com.zigis.paleontologas.features.main.routing.MainRouter
import com.zigis.paleontologas.features.settings.stories.about.AboutViewModel
import com.zigis.paleontologas.features.settings.stories.language.LanguageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { MainRouter(get(), get()) }
}