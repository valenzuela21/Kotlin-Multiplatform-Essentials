package com.kmp.splitbill.views

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmp.splitbill.components.MainCard
import com.kmp.splitbill.components.MainIcon
import com.kmp.splitbill.components.MainRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Split Bill", fontWeight = FontWeight.Bold) }
            )
        }
    ) { padding ->
       ContentHomeView(modifier = Modifier.padding(padding))
    }
}

@Composable
fun ContentHomeView(modifier: Modifier){
    //var amount by remember { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }
    val options = listOf(0,10,15,25,30)
    var selectedTip by remember { mutableStateOf(10) }
    var numberPersons by remember { mutableStateOf(1) }
    var totalTip by remember { mutableStateOf(0.0) }
    var total by remember { mutableStateOf(0.0) }
    var totalByPersons by remember { mutableStateOf(0.0) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier
            .pointerInput(Unit){
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
    ) {
        MainCard("Total Bill Amount"){
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it},
                label = { Text("Amount") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
                )

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                options.forEach { option ->
                    FilterChip(
                        selected = selectedTip == option,
                        onClick = { selectedTip = option },
                        label = { Text("$option%")}
                    )
                }
            }
            Text("Number of persons")
            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                MainIcon("down",Icons.Default.ArrowCircleDown){
                   if (numberPersons > 1 ) numberPersons--
                }
                Text(numberPersons.toString(), fontSize = 50.sp)

                MainIcon("up",Icons.Default.ArrowCircleUp){
                    numberPersons++
                }
            }
            Button({
                    if (!amount.isEmpty()){
                        totalTip = amount.toDouble() * (selectedTip.toDouble() / 100)
                        total = amount.toDouble() + totalTip
                        totalByPersons = calculate(amount,selectedTip,numberPersons)
                    }
                focusManager.clearFocus()
            }){
                Text("Calculate")
            }

        }

        MainCard("Bill Summary"){
            MainRow("Tip Amount",totalTip.roundTwoDecimals())
            MainRow("Total",total.roundTwoDecimals())
            Surface(
                color = Color(0xFFE5F9E7),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {

                Text(
                    "Each Person Pays $${totalByPersons.roundTwoDecimals()}",
                    color = Color(0xFF2E7D32),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                )
            }
        }
    }
}

fun calculate(amount:String,tip:Int,persons:Int): Double {
    val tipRes = amount.toDouble() * (tip.toDouble() / 100)
    val totalWithTip = amount.toDouble() + tipRes
    return totalWithTip / persons
}

fun Double.roundTwoDecimals(): Double {
    return kotlin.math.round(this * 100) / 100
}




























