package com.kmp.palette.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun androidDatabaseBuilder(context: Context): RoomDatabase.Builder<RoomDb> {
    val dbFile = context.applicationContext.getDatabasePath("paletteDb.db")
    return Room.databaseBuilder(
        context,
        dbFile.absolutePath
    )

}