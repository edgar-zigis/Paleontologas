package com.zigis.paleontologas.application.di

import com.zigis.paleontologas.periods.usecases.LifeFormListUseCase
import com.zigis.paleontologas.periods.usecases.PeriodListUseCase
import com.zigis.paleontologas.quiz.usecases.QuizGenerateQuestionsUseCase
import com.zigis.paleontologas.quiz.usecases.QuizProgressUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { PeriodListUseCase(get(), get()) }
    factory { LifeFormListUseCase(get()) }
    factory { QuizProgressUseCase(get()) }
    factory { QuizGenerateQuestionsUseCase(get()) }
}