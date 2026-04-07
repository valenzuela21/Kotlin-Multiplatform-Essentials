package com.kmp.itunessearchapi.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmp.itunessearchapi.viewModel.ItunesViewModel

@Composable
fun TestView(viewModel: ItunesViewModel = ItunesViewModel()) {
    val itunesList by viewModel.itunesList.collectAsState()
    var search by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = search, onValueChange = { search = it })
        Button({ viewModel.getSearch(search) }){
            Text("Search")
        }
        LazyColumn {
            items(itunesList){ item ->
                item.name?.let { Text(it) }
            }
        }
    }
}