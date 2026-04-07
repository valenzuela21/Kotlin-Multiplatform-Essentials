package com.kmp.palette.usesCases.colors

import com.kmp.palette.models.ColorModel
import com.kmp.palette.repositories.ColorRepository

class DeleteColorUseCase(private val colorRepository: ColorRepository) {
    suspend operator fun invoke(coloItem: ColorModel){
        colorRepository.deleteColor(coloItem)
    }
}