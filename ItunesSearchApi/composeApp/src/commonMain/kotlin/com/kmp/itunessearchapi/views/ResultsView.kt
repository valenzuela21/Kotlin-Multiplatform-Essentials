package com.kmp.itunessearchapi.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kmp.itunessearchapi.components.ImageItem
import com.kmp.itunessearchapi.components.MainTextField
import com.kmp.itunessearchapi.navigation.Detail
import com.kmp.itunessearchapi.viewModel.MainViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsView(navController: NavController, search: String) {
    val viewModel = koinViewModel<MainViewModel>()
    //val listItunes by viewModel.itunesList.collectAsState()
    val listItunes by viewModel.filteredList.collectAsState()
    val isLoading = listItunes.isEmpty()
    val query = viewModel.searchQuery

    LaunchedEffect(Unit) {
        viewModel.getSearch(search)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${listItunes.count()} Results") },
                navigationIcon = {
                    IconButton({ navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowCircleLeft, "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            Column {
                MainTextField(query, onValueChange = { viewModel.onSearchValue(it) })
                when {
                    isLoading -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    else -> {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(120.dp),
                            contentPadding = PaddingValues(4.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(listItunes) { item ->
                                ImageItem(
                                    image = item.image,
                                    name = item.name
                                ) {
                                    navController.navigate(Detail(
                                        item.name,
                                        item.image,
                                        item.kind,
                                        item.desc
                                    ))
                                }
                            }
                        }
                    }
                }
            }

        }
    }

}