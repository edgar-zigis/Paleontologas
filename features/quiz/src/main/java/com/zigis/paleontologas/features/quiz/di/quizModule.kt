package com.zigis.paleontologas.features.quiz.di

import com.google.firebase.auth.FirebaseAuth
import com.zigis.paleontologas.features.quiz.data.QuestionDatabase
import com.zigis.paleontologas.features.quiz.factories.QuizPlayerFactory
import com.zigis.paleontologas.features.quiz.managers.CountryManager
import com.zigis.paleontologas.features.quiz.managers.FirebaseDataManager
import com.zigis.paleontologas.features.quiz.managers.PaywallManager
import com.zigis.paleontologas.features.quiz.managers.QuizGameProcessor
import com.zigis.paleontologas.features.quiz.repositories.QuestionRepository
import com.zigis.paleontologas.features.quiz.routing.QuizRouter
import com.zigis.paleontologas.features.quiz.stories.game.QuizGameViewModel
import com.zigis.paleontologas.features.quiz.stories.finalresult.QuizFinalResultViewModel
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressViewModel
import com.zigis.paleontologas.features.quiz.usecases.QuizGenerateQuestionsUseCase
import com.zigis.paleontologas.features.quiz.usecases.QuizProgressUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val quizModule = module {
    factory { QuizGameProcessor(get(), get(), get()) }
    single { QuizRouter(get()) }
    single { FirebaseDataManager(androidContext()) }
    single { CountryManager() }
    single { QuizPlayerFactory(get(), get()) }
    single { PaywallManager(get(), get()) }

    single { FirebaseAuth.getInstance() }

    factory { QuizProgressUseCase(get()) }
    factory { QuizGenerateQuestionsUseCase(get()) }

    single { QuestionDatabase.getInstance(androidContext()) }
    single { get<QuestionDatabase>().questionDao() }
    single { QuestionRepository(androidContext(), get()) }

    viewModel { QuizGameViewModel(get(), get(), get(), get()) }
    viewModel { QuizProgressViewModel(androidContext(), get(), get(),get(), get(), get(), get(), get()) }
    viewModel { QuizFinalResultViewModel(get(), get()) }
}