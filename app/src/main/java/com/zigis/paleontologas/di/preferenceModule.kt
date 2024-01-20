package com.zigis.paleontologas.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val preferenceModule = module {
    single(named("applicationPreferences")) {
        provideApplicationSharedPreferences(androidApplication())
    }
}

private fun provideApplicationSharedPreferences(application: Application): SharedPreferences {
    return application.getSharedPreferences("applicationPreferences", Context.MODE_PRIVATE)
}