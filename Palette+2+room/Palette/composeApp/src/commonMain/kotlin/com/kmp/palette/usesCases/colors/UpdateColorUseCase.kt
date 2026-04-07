package com.kmp.palette.usesCases.colors

import com.kmp.palette.models.ColorModel
import com.kmp.palette.repositories.ColorRepository

class UpdateColorUseCase(private val colorRepository: ColorRepository) {
    suspend operator fun invoke(colorItem: ColorModel, r: Int, g: Int, b: Int) {
        val hex = ColorModel.rgbToHex(r, g, b)
        val rgb = "RGB($r, $g, $b)"

        val updateColor = colorItem.copy(
            red = r,
            green = g,
            blue = b,
            hex = hex,
            rgb = rgb
        )
        colorRepository.updateColor(updateColor)
    }
}