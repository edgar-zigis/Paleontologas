package com.zigis.paleontologas.di

import com.zigis.paleontologas.features.library.routers.LibraryRouter
import org.koin.dsl.module

val libraryModule = module {
    single { LibraryRouter(get()) }
}