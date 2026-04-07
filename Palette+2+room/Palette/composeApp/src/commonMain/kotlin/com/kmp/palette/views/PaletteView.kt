package com.kmp.palette.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kmp.palette.components.ColorCard
import com.kmp.palette.components.MainAlert
import com.kmp.palette.components.MainSlider
import com.kmp.palette.copyToClipboard
import com.kmp.palette.models.ColorModel
import com.kmp.palette.navigation.DetailColor
import com.kmp.palette.viewModels.ColorViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaletteView(
    navController: NavController,
    id: Int,
    name: String,
) {
    val viewModel = koinViewModel<ColorViewModel>()
    var showAlert by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                { Text(name) },
                actions = {
                    IconButton({ showAlert = true }) {
                        Icon(Icons.Default.Delete, "Delete")
                    }
                    IconButton({ viewModel.copyAll(idPalette = id) }) {
                        Icon(Icons.Default.CopyAll, "Copy all")
                    }
                    IconButton({ navController.navigate(DetailColor(id,name)) }) {
                        Icon(Icons.Default.Palette, "Palette")
                    }
                },
                navigationIcon = {
                    IconButton({ navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowCircleLeft, "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                { viewModel.insertColor(id) },
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, "Add")
            }
        }
    ) { padding ->
        ContentPaletteView(modifier = Modifier.padding(padding), id)
        if (showAlert) {
            MainAlert(
                "Remove all colors",
                "Are you sure you want to remove all colors?",
                {
                    viewModel.deleteColorById(id)
                    showAlert = false
                },
                { showAlert = false }
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentPaletteView(modifier: Modifier, id: Int) {
    val viewModel = koinViewModel<ColorViewModel>()
    val colors by viewModel.getColors(idPalette = id).collectAsState(null)
    val modalState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showModal by remember { mutableStateOf(false) }
    var red by remember { mutableStateOf(0f) }
    var green by remember { mutableStateOf(0f) }
    var blue by remember { mutableStateOf(0f) }
    var selectedColor by remember { mutableStateOf<ColorModel?>(null) }
    var showAlert by remember { mutableStateOf(false) }

    LazyColumn(modifier) {
        items(colors.orEmpty()) { color ->
            ColorCard(
                color.hex,
                color.rgb,
                {
                    red = color.red.toFloat()
                    green = color.green.toFloat()
                    blue = color.blue.toFloat()
                    selectedColor = color
                    showModal = true
                },
                {
                    copyToClipboard(color.hex)
                },
                {
                    selectedColor = color
                    showAlert = true
                    //viewModel.deleteColor(color)
                }
            )
        }
    }

    if (showAlert) {
        MainAlert(
            "Remove color",
            "Are you sure you want to remove the color?",
            {
                selectedColor?.let {
                    viewModel.deleteColor(it)
                    showAlert = false
                }
            },
            { showAlert = false }
        )
    }

    if (showModal) {
        ModalBottomSheet(
            onDismissRequest = { showModal = false },
            sheetState = modalState
        ) {
            Column(
                modifier = Modifier
                    .padding(40.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Edit Color", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                Spacer(modifier = Modifier.height(30.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(80.dp)
                        .shadow(elevation = 12.dp)
                        .background(Color(red.toInt(), green.toInt(), blue.toInt()))
                )
                Spacer(modifier = Modifier.height(25.dp))
                MainSlider(
                    value = red,
                    onValueChange = { red = it },
                    color = Color.Red
                )
                MainSlider(
                    value = green,
                    onValueChange = { green = it },
                    color = Color.Green
                )
                MainSlider(
                    value = blue,
                    onValueChange = { blue = it },
                    color = Color.Blue
                )
                OutlinedButton({
                    selectedColor?.let {
                        viewModel.editColor(it, red.toInt(), green.toInt(), blue.toInt())
                    }
                    showModal = false
                }) {
                    Text("Change Color", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}




