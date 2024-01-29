package com.zigis.paleontologas.features.main

import com.zigis.paleontologas.features.main.routers.MainRouter
import com.zigis.paleontologas.features.main.stories.about.AboutViewModel
import com.zigis.paleontologas.features.main.stories.home.HomeViewModel
import com.zigis.paleontologas.features.main.stories.language.LanguageViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { MainRouter(get(), get()) }

    viewModel { HomeViewModel(get(), get(), get(), get()) }
    viewModel { AboutViewModel(get(), get()) }
    viewModel { LanguageViewModel(get(), get()) }
}