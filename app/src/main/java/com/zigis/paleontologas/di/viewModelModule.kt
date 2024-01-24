package com.zigis.paleontologas.di

import com.zigis.paleontologas.features.launcher.stories.main.LauncherViewModel
import com.zigis.paleontologas.features.main.stories.home.HomeViewModel
import com.zigis.paleontologas.features.main.stories.about.AboutViewModel
import com.zigis.paleontologas.features.main.stories.language.LanguageViewModel
import com.zigis.paleontologas.features.library.stories.lifeforms.LifeFormViewModel
import com.zigis.paleontologas.features.library.stories.periods.PeriodViewModel
import com.zigis.paleontologas.features.quiz.stories.game.QuizGameViewModel
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LauncherViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { PeriodViewModel(get(), get(), get()) }
    viewModel { LifeFormViewModel(get()) }
    viewModel { AboutViewModel(get()) }
    viewModel { LanguageViewModel(get()) }
    viewModel { QuizProgressViewModel(get(), get()) }
    viewModel { QuizGameViewModel(get()) }
}