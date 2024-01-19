package com.zigis.paleontologas.di

import com.zigis.paleontologas.features.library.usecases.LifeFormListUseCase
import com.zigis.paleontologas.features.library.usecases.PeriodListUseCase
import com.zigis.paleontologas.features.quiz.usecases.QuizGenerateQuestionsUseCase
import com.zigis.paleontologas.features.quiz.usecases.QuizProgressUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { PeriodListUseCase(get(), get()) }
    factory { LifeFormListUseCase(get()) }
    factory { QuizProgressUseCase(get()) }
    factory { QuizGenerateQuestionsUseCase(get()) }
}