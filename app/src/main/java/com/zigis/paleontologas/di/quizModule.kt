package com.zigis.paleontologas.di

import com.zigis.paleontologas.features.quiz.managers.QuizGameProcessor
import com.zigis.paleontologas.features.quiz.routers.QuizRouter
import org.koin.dsl.module

val quizModule = module {
    factory { QuizGameProcessor(get(), get()) }
    single { QuizRouter(get()) }
}