package com.kmp.splitbill.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MainIcon(desc:String,icon: ImageVector,onClick: () -> Unit){
    IconButton(onClick){
        Icon(
            imageVector = icon,
            desc,
            modifier = Modifier.size(50.dp)
        )
    }
}