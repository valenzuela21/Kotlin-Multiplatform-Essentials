package com.kmp.itunessearchapi

import android.app.Application
import com.kmp.itunessearchapi.di.initKoin

class ItunesApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }
}