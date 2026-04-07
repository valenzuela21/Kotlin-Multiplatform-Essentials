package com.kmp.palette.usesCases.palette

import com.kmp.palette.models.PaletteModel
import com.kmp.palette.repositories.PaletteRepository

class InsertPaletteUseCase(private val paletteRepository: PaletteRepository) {
    suspend operator fun invoke(paletteItem: PaletteModel){
        paletteRepository.insertPalette(paletteItem)
    }
}