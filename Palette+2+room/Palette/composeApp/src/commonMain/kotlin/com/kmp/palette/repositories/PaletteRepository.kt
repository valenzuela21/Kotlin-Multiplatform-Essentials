package com.kmp.palette.repositories

import com.kmp.palette.models.PaletteModel
import com.kmp.palette.room.RoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class PaletteRepository(private val database: RoomDb) {

    private val dispatchers = Dispatchers.IO

    suspend fun insertPalette(paletteItem: PaletteModel){
        with(dispatchers){
            database.paletteDao().insertPalette(paletteItem)
        }
    }

    suspend fun updatePalette(paletteItem: PaletteModel){
        with(dispatchers){
            database.paletteDao().updatePalette(paletteItem)
        }
    }

    suspend fun deletePalette(paletteItem: PaletteModel){
        with(dispatchers){
            database.paletteDao().deletePalette(paletteItem)
        }
    }

    fun getAllPalettes(): Flow<List<PaletteModel>?>{
        return database.paletteDao().getAllPalettes()
    }

}