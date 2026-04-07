package com.kmp.palette.usesCases.colors

import com.kmp.palette.models.ColorModel
import com.kmp.palette.repositories.ColorRepository

class DeleteByIdUseCase(private val colorRepository: ColorRepository) {
    suspend operator fun invoke(idPalette: Int){
        colorRepository.deleteById(idPalette)
    }
}