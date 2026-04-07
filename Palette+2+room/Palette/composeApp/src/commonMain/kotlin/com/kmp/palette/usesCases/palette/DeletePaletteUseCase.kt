package com.kmp.palette.usesCases.palette

import com.kmp.palette.models.PaletteModel
import com.kmp.palette.repositories.ColorRepository
import com.kmp.palette.repositories.PaletteRepository

class DeletePaletteUseCase(
    private val paletteRepository: PaletteRepository,
    private val colorRepository: ColorRepository
) {
    suspend operator fun invoke(paletteItem: PaletteModel){
        colorRepository.deleteById(paletteItem.id)
        paletteRepository.deletePalette(paletteItem)
    }
}