package com.kmp.splitbill

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kmp.splitbill.ui.theme.colors
import com.kmp.splitbill.views.HomeView

@Composable
fun App() {
    MaterialTheme(colorScheme = colors) {
        HomeView()
    }
}