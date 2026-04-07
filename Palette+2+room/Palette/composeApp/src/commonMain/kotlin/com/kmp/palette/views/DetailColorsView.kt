package com.kmp.palette.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kmp.palette.navigation.DetailColor
import com.kmp.palette.viewModels.ColorViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailColorsView(navController: NavController, color: DetailColor){
    val viewModel = koinViewModel<ColorViewModel>()
    val colors by viewModel.getColors(color.id).collectAsState(null)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(color.name) },
                navigationIcon = {
                    IconButton( { navController.popBackStack() }){
                        Icon(Icons.Default.ArrowCircleLeft, "back")
                    }
                }
            )
        }
    ) {
        paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(colors.orEmpty()){ color ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color(color.hex.removePrefix("#").toLong(16) or 0xFF000000))
                )
            }
        }
    }
}