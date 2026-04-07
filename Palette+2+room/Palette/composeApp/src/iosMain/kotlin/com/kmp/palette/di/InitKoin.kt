package com.kmp.palette.di

import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(iosDatabaseModule, sharedModule)
    }
}