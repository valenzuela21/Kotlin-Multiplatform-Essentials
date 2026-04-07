package com.kmp.palette.di

import androidx.room.RoomDatabase
import com.kmp.palette.room.RoomDb
import com.kmp.palette.room.iosDatabaseBuilder
import org.koin.dsl.module

val iosDatabaseModule = module {
    single<RoomDatabase.Builder<RoomDb>> { iosDatabaseBuilder() }
}