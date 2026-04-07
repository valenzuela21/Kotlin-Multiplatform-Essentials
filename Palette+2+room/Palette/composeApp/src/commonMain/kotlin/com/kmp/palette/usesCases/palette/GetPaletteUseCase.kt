package com.kmp.palette.usesCases.palette

import com.kmp.palette.models.PaletteModel
import com.kmp.palette.repositories.PaletteRepository
import kotlinx.coroutines.flow.Flow

class GetPaletteUseCase(private val paletteRepository: PaletteRepository) {
    operator fun invoke(): Flow<List<PaletteModel>?>{
        return paletteRepository.getAllPalettes()
    }
}