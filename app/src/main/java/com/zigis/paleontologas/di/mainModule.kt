package com.zigis.paleontologas.di

import com.zigis.paleontologas.features.main.routers.MainRouter
import org.koin.dsl.module

val mainModule = module {
    single { MainRouter(get(), get()) }
}