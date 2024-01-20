package com.zigis.paleontologas.di

import com.zigis.paleontologas.features.quiz.managers.QuizGameProcessor
import org.koin.dsl.module

val quizModule = module {
    factory { QuizGameProcessor(get(), get()) }
}