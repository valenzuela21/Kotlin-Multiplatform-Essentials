package com.kmp.palette

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.kmp.palette.navigation.NavManager

@Composable

fun App() {
    MaterialTheme(colorScheme = lightColorScheme(
        background = Color.LightGray
    )) {
        NavManager()
    }
}