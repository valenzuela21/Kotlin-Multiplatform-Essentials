package com.kmp.palette.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmp.palette.models.PaletteModel
import com.kmp.palette.usesCases.palette.DeletePaletteUseCase
import com.kmp.palette.usesCases.palette.GetPaletteUseCase
import com.kmp.palette.usesCases.palette.InsertPaletteUseCase
import com.kmp.palette.usesCases.palette.UpdatePaletteUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PaletteViewModel(
    private val insertPaletteUseCase: InsertPaletteUseCase,
    private val updatePaletteUseCase: UpdatePaletteUseCase,
    private val getPaletteUseCase: GetPaletteUseCase,
    private val deletePaletteUseCase: DeletePaletteUseCase
): ViewModel() {

    fun insertPalette(paletteItem: PaletteModel){
        viewModelScope.launch { insertPaletteUseCase(paletteItem)
        }
    }

    fun updatePalette(paletteItem: PaletteModel){
        viewModelScope.launch { updatePaletteUseCase(paletteItem)
        }
    }

    fun getPalettes(): Flow<List<PaletteModel>?>{
        return getPaletteUseCase()
    }

    fun deletePalette(paletteItem: PaletteModel){
        viewModelScope.launch { deletePaletteUseCase(paletteItem) }
    }

}