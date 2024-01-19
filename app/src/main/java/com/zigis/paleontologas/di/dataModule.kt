package com.zigis.paleontologas.di

import com.zigis.paleontologas.features.library.data.LifeFormDatabase
import com.zigis.paleontologas.features.library.data.PeriodDatabase
import com.zigis.paleontologas.features.quiz.data.QuestionDatabase
import com.zigis.paleontologas.features.library.repositories.LifeFormRepository
import com.zigis.paleontologas.features.library.repositories.PeriodRepository
import com.zigis.paleontologas.features.quiz.repositories.QuestionRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { PeriodDatabase.getInstance(androidContext()) }
    single { get<PeriodDatabase>().periodDao() }
    single { PeriodRepository(androidContext(), get()) }

    single { LifeFormDatabase.getInstance(androidContext()) }
    single { get<LifeFormDatabase>().lifeFormDao() }
    single { LifeFormRepository(androidContext(), get()) }

    single { QuestionDatabase.getInstance(androidContext()) }
    single { get<QuestionDatabase>().questionDao() }
    single { QuestionRepository(androidContext(), get()) }
}