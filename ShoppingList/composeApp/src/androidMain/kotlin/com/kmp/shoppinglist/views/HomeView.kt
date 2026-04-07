package com.kmp.shoppinglist.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmp.shoppinglist.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: SharedViewModel = SharedViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping List", fontWeight = FontWeight.Bold) }
            )
        }
    ) { paddingValues ->
        val articles by viewModel.articles.collectAsState()
        val total by viewModel.total.collectAsState()
        var article by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = article,
                    onValueChange = { article = it },
                    label = { Text("Article") },
                    modifier = Modifier.weight(0.7f)
                )
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Price") },
                    modifier = Modifier.weight(0.3f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
            FilledTonalButton(
                {
                    if (!article.isEmpty() and !price.isEmpty()){
                        viewModel.addArticle(article, price.toDouble())
                        article = ""
                        price = ""
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text("+ Add")
            }
            Text("Total: $${total}", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            LazyColumn {
                items(articles) { item ->
                    ListItem(
                        headlineContent = { Text(item.article) },
                        trailingContent = {
                            Text("$${item.price}", fontSize = 20.sp)
                        },
                        leadingContent = {
                            IconButton({ viewModel.deleteArticle(item.id) }) {
                                Icon(
                                    Icons.Default.Delete,
                                    "Delete",
                                    tint = Color.Red
                                )
                            }
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}