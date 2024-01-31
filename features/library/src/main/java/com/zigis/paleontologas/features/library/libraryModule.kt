package com.zigis.paleontologas.features.library

import com.zigis.paleontologas.features.library.data.LifeFormDatabase
import com.zigis.paleontologas.features.library.data.PeriodDatabase
import com.zigis.paleontologas.features.library.repositories.LifeFormRepository
import com.zigis.paleontologas.features.library.repositories.PeriodRepository
import com.zigis.paleontologas.features.library.routers.LibraryRouter
import com.zigis.paleontologas.features.library.stories.formavitae.FormaVitaeViewModel
import com.zigis.paleontologas.features.library.stories.geologicalperiod.GeologicalPeriodViewModel
import com.zigis.paleontologas.features.library.factories.GeologicalPeriodAdapterItemFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val libraryModule = module {
    single { LibraryRouter(get()) }

    factory { GeologicalPeriodAdapterItemFactory(get()) }

    single { PeriodDatabase.getInstance(androidContext()) }
    single { get<PeriodDatabase>().periodDao() }
    single { PeriodRepository(androidContext(), get()) }

    single { LifeFormDatabase.getInstance(androidContext()) }
    single { get<LifeFormDatabase>().lifeFormDao() }
    single { LifeFormRepository(androidContext(), get()) }

    viewModel { GeologicalPeriodViewModel(get(), get(), get()) }
    viewModel { FormaVitaeViewModel(get(), get()) }
}