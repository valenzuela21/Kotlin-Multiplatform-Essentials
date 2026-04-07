package com.kmp.palette.components

import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MainSlider(value:Float, color: Color, onValueChange: (Float) -> Unit ){
    Slider(
        value,
        onValueChange,
        valueRange = 0f..255f,
        colors = SliderDefaults.colors(
            thumbColor = color,
            activeTickColor = color,
            activeTrackColor = color,
            inactiveTickColor = Color.LightGray
        )

    )
}
