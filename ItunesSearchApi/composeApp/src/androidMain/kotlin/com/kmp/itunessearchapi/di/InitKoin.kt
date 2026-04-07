package com.kmp.itunessearchapi.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun initKoin(application: Application) = startKoin {
    androidContext(application)
    modules(
        sharedModule
    )
}