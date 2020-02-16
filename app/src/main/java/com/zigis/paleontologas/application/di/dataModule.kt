package com.zigis.paleontologas.application.di

import com.zigis.paleontologas.periods.data.databases.LifeFormDatabase
import com.zigis.paleontologas.periods.data.databases.PeriodDatabase
import com.zigis.paleontologas.quiz.data.databases.QuestionDatabase
import com.zigis.paleontologas.periods.repositories.LifeFormRepository
import com.zigis.paleontologas.periods.repositories.PeriodRepository
import com.zigis.paleontologas.quiz.repositories.QuestionRepository
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