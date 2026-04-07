package com.kmp.palette.usesCases.colors

import com.kmp.palette.models.ColorModel
import com.kmp.palette.repositories.ColorRepository
import kotlin.random.Random

class InsertColorUseCase(private val colorRepository: ColorRepository) {
    suspend operator fun invoke(idPalette:Int){
        val r = Random.nextInt(256)
        val g = Random.nextInt(256)
        val b = Random.nextInt(256)

        val hex = ColorModel.rgbToHex(r, g, b)
        val rgb = "RGB($r, $g, $b)"

        val newColor = ColorModel(
            idPalette = idPalette,
            red = r,
            green = g,
            blue = b,
            hex = hex,
            rgb = rgb
        )
        colorRepository.insertColor(newColor)
    }
}