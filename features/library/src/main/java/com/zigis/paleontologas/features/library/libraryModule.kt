package com.zigis.paleontologas.features.library

import com.zigis.paleontologas.features.library.data.LifeFormDatabase
import com.zigis.paleontologas.features.library.data.PeriodDatabase
import com.zigis.paleontologas.features.library.repositories.LifeFormRepository
import com.zigis.paleontologas.features.library.repositories.PeriodRepository
import com.zigis.paleontologas.features.library.routing.LibraryRouter
import com.zigis.paleontologas.features.library.stories.lifeform.LifeFormViewModel
import com.zigis.paleontologas.features.library.stories.geologicalperiod.GeologicalPeriodViewModel
import com.zigis.paleontologas.features.library.factories.GeologicalPeriodListItemFactory
import com.zigis.paleontologas.features.library.factories.TimelineListItemFactory
import com.zigis.paleontologas.features.library.stories.timeline.TimelineViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val libraryModule = module {
    single { LibraryRouter(get()) }

    factory { TimelineListItemFactory(get(), get()) }
    factory { GeologicalPeriodListItemFactory(get()) }

    single { PeriodDatabase.getInstance(androidContext()) }
    single { get<PeriodDatabase>().periodDao() }
    single { PeriodRepository(androidContext(), get()) }

    single { LifeFormDatabase.getInstance(androidContext()) }
    single { get<LifeFormDatabase>().lifeFormDao() }
    single { LifeFormRepository(androidContext(), get()) }

    viewModel { TimelineViewModel(get(), get()) }
    viewModel { GeologicalPeriodViewModel(get(), get(), get()) }
    viewModel { LifeFormViewModel(get(), get()) }
}