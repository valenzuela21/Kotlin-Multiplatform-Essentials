package com.kmp.splitbill.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MainRow(title:String, total:Double){
    Row(modifier = Modifier.fillMaxWidth()
        .padding(10.dp)
    ) {
        Text(title, color = Color.Gray, modifier = Modifier.weight(1f))
        Text("$$total", fontWeight = FontWeight.Bold)
    }
}