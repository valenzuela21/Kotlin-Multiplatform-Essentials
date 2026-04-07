package com.kmp.itunessearchapi

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.kmp.itunessearchapi.navigation.NavManager
import com.kmp.itunessearchapi.views.TestView

@Composable
fun App() {
    MaterialTheme(colorScheme = lightColorScheme(primary = Color.Black)) {
        //TestView()
        NavManager()
    }
}