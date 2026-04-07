package com.kmp.itunessearchapi.di

import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(sharedModule)
    }
}