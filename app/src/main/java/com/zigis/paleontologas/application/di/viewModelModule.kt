package com.zigis.paleontologas.application.di

import com.zigis.paleontologas.launcher.viewmodels.LauncherViewModel
import com.zigis.paleontologas.main.viewmodels.MainMenuViewModel
import com.zigis.paleontologas.other.viewmodels.AboutViewModel
import com.zigis.paleontologas.other.viewmodels.LanguageViewModel
import com.zigis.paleontologas.periods.viewmodels.LifeFormViewModel
import com.zigis.paleontologas.periods.viewmodels.PeriodViewModel
import com.zigis.paleontologas.quiz.viewmodels.QuizGameViewModel
import com.zigis.paleontologas.quiz.viewmodels.QuizProgressViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LauncherViewModel(get()) }
    viewModel { MainMenuViewModel(get()) }
    viewModel { PeriodViewModel(get(), get()) }
    viewModel { LifeFormViewModel(get()) }
    viewModel { AboutViewModel(get()) }
    viewModel { LanguageViewModel(get()) }
    viewModel { QuizProgressViewModel(get()) }
    viewModel { QuizGameViewModel(get()) }
}