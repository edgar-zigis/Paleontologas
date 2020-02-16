package com.zigis.paleontologas.application.di

import com.zigis.paleontologas.quiz.managers.QuizGameProcessor
import org.koin.dsl.module

val quizModule = module {
    factory { QuizGameProcessor(get(), get()) }
}