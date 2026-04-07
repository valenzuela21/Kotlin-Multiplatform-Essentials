package com.kmp.palette.di

import androidx.room.RoomDatabase
import com.kmp.palette.room.RoomDb
import com.kmp.palette.room.androidDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidDatabaseModule = module {
    single <RoomDatabase.Builder<RoomDb>> { androidDatabaseBuilder(androidContext()) }
}