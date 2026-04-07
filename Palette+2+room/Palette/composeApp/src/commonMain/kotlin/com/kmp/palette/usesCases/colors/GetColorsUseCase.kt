package com.kmp.palette.usesCases.colors

import com.kmp.palette.models.ColorModel
import com.kmp.palette.repositories.ColorRepository
import kotlinx.coroutines.flow.Flow

class GetColorsUseCase(private val colorRepository: ColorRepository) {
    operator fun invoke(idPalette:Int): Flow<List<ColorModel>?>{
        return colorRepository.getColors(idPalette)
    }
}