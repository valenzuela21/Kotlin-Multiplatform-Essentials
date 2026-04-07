package com.kmp.palette

import android.app.Application
import com.kmp.palette.di.androidDatabaseModule
import com.kmp.palette.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PaletteApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PaletteApplication)
            modules(androidDatabaseModule, sharedModule)
        }
    }

}