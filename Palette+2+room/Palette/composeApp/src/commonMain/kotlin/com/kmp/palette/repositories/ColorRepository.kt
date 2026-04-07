package com.kmp.palette.repositories

import com.kmp.palette.models.ColorModel
import com.kmp.palette.room.RoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class ColorRepository(private val database: RoomDb) {
    private val dispatchers = Dispatchers.IO

    suspend fun insertColor(colorItem: ColorModel){
        with(dispatchers){
            database.colorDao().insertColor(colorItem)
        }
    }

    suspend fun updateColor(colorItem: ColorModel){
        with(dispatchers){
            database.colorDao().updateColor(colorItem)
        }
    }

    suspend fun deleteColor(colorItem: ColorModel){
        with(dispatchers){
            database.colorDao().deleteColor(colorItem)
        }
    }

    suspend fun deleteById(idPalette:Int){
        with(dispatchers){
            database.colorDao().deleteById(idPalette)
        }
    }

    fun getColors(idPalette: Int): Flow<List<ColorModel>?>{
        return database.colorDao().getColors(idPalette)
    }

}