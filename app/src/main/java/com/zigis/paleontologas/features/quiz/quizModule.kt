package com.zigis.paleontologas.features.quiz

import com.zigis.paleontologas.features.quiz.data.QuestionDatabase
import com.zigis.paleontologas.features.quiz.managers.QuizGameProcessor
import com.zigis.paleontologas.features.quiz.repositories.QuestionRepository
import com.zigis.paleontologas.features.quiz.routers.QuizRouter
import com.zigis.paleontologas.features.quiz.stories.game.QuizGameViewModel
import com.zigis.paleontologas.features.quiz.stories.mark.QuizMarkViewModel
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressViewModel
import com.zigis.paleontologas.features.quiz.usecases.QuizGenerateQuestionsUseCase
import com.zigis.paleontologas.features.quiz.usecases.QuizProgressUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val quizModule = module {
    factory { QuizGameProcessor(get(), get()) }
    single { QuizRouter(get()) }

    factory { QuizProgressUseCase(get()) }
    factory { QuizGenerateQuestionsUseCase(get()) }

    single { QuestionDatabase.getInstance(androidContext()) }
    single { get<QuestionDatabase>().questionDao() }
    single { QuestionRepository(androidContext(), get()) }

    viewModel { QuizGameViewModel(get(), get()) }
    viewModel { QuizProgressViewModel(get(), get()) }
    viewModel { QuizMarkViewModel() }
}