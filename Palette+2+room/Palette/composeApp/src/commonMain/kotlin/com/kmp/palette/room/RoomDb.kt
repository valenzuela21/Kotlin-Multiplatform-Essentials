package com.kmp.palette.room

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.kmp.palette.dao.ColorDao
import com.kmp.palette.dao.PaletteDao
import com.kmp.palette.models.ColorModel
import com.kmp.palette.models.PaletteModel

@Database(entities = [PaletteModel::class, ColorModel::class], version = 2, exportSchema = true)
@ConstructedBy(AppDataBaseConstructor::class)
abstract class RoomDb: RoomDatabase() {
    abstract fun paletteDao(): PaletteDao
    abstract fun colorDao(): ColorDao
}
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDataBaseConstructor: RoomDatabaseConstructor<RoomDb>{
    override fun initialize(): RoomDb
}