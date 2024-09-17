package com.zigis.paleontologas.features.main.di

import com.zigis.paleontologas.features.main.routing.MainRouter
import org.koin.dsl.module

val mainModule = module {
    single { MainRouter(get(), get()) }
}