package com.kmp.palette.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kmp.palette.components.ColorCard
import com.kmp.palette.components.IconTitle
import com.kmp.palette.components.MainAlert
import com.kmp.palette.components.MainSlider
import com.kmp.palette.components.ModalPalette
import com.kmp.palette.copyToClipboard
import com.kmp.palette.models.PaletteModel
import com.kmp.palette.navigation.Palette
import com.kmp.palette.viewModels.ColorViewModel
import com.kmp.palette.viewModels.PaletteViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import palette.composeapp.generated.resources.Res
import palette.composeapp.generated.resources.palette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController) {
    val viewModel = koinViewModel<PaletteViewModel>()
    var showModal by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                {
                    Row {
                        Image(

                            painterResource(Res.drawable.palette),
                            "logo",
                            modifier = Modifier.height(25.dp)
                        )
                    }
                },
                actions = {
                    IconButton({ showModal = true }) {
                        Icon(Icons.Default.Add, "Add")
                    }
                }
            )
        }
    ) { padding ->
        ContentHomeView(modifier = Modifier.padding(padding), navController)
        if (showModal) {
            ModalPalette(
                palette = null,
                onDismiss = { showModal = false },
                onSave = { viewModel.insertPalette(it) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentHomeView(modifier: Modifier, navController: NavController) {
    val viewModel = koinViewModel<PaletteViewModel>()
    val palettes by viewModel.getPalettes().collectAsState(null)
    var expanded by remember { mutableStateOf<Int?>(null) }
    var showModal by remember { mutableStateOf(false) }
    var selectedPalette by remember { mutableStateOf<PaletteModel?>(null) }
    var showAlert by remember { mutableStateOf(false) }

    LazyColumn(modifier) {
        items(palettes.orEmpty()) { item ->
            HorizontalDivider()
            ListItem(
                headlineContent = { Text(item.name) },
                supportingContent = { Text(item.desc, color = Color.Gray) },
                leadingContent = {
                    Box {
                        IconButton({ expanded = item.id }) {
                            Icon(Icons.Default.MoreVert, "more")
                        }
                        DropdownMenu(
                            expanded = expanded == item.id,
                            onDismissRequest = { expanded = null },
                            modifier = Modifier.background(Color(0xFF2B3667))
                        ) {
                            DropdownMenuItem(
                                text = { IconTitle("Edit", Icons.Default.Edit) },
                                onClick = {
                                    selectedPalette = item
                                    showModal = true
                                    expanded = null
                                }
                            )
                            DropdownMenuItem(
                                text = { IconTitle("Delete", Icons.Default.Delete) },
                                onClick = {
                                    selectedPalette = item
                                    showAlert = true
                                    expanded = null
                                }
                            )
                        }
                    }
                },
                trailingContent = {
                    IconButton({}) {
                        Icon(Icons.Default.ArrowCircleRight, "next")
                    }
                },
                modifier = Modifier.clickable{
                    navController.navigate(Palette(item.id,item.name))
                }
            )
            HorizontalDivider()

        }
    }
    // modal edit
    if (showModal) {
        ModalPalette(
            palette = selectedPalette,
            onDismiss = { showModal = false },
            onSave = { viewModel.updatePalette(it) }
        )
    }
    // alert
    if (showAlert) {
        MainAlert(
            "Remove palette",
            "Are you sure you want to remove the palette?",
            {
                selectedPalette?.let {
                    viewModel.deletePalette(it)
                    showAlert = false
                }
            },
            { showAlert = false }
        )
    }
}


