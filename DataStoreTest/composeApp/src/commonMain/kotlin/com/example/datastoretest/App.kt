package com.example.datastoretest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.datastoretest.components.AppCard
import com.example.datastoretest.datastore.AppModule
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    val preferencesRepository = remember { AppModule.preferenceRepository }
    val scope = rememberCoroutineScope()

    val savedUserName by preferencesRepository.userName.collectAsState(initial = "User")
    val visitCount by preferencesRepository.visitCount.collectAsState(initial = 0)
    val isDarkMode by preferencesRepository.isDarkModeEnabled.collectAsState(initial = false)

    var nameInput by remember { mutableStateOf("") }
    val colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme()

    MaterialTheme(colorScheme = colorScheme) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .safeContentPadding()
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "DataStore Test",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))

            AppCard(containerColor = MaterialTheme.colorScheme.primaryContainer) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Hello, $savedUserName",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "App Visits, $visitCount",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                }
            }
            AppCard(containerColor = MaterialTheme.colorScheme.inverseOnSurface) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Change name", fontWeight = FontWeight.Medium)
                    OutlinedTextField(
                        value = nameInput,
                        onValueChange = { nameInput = it },
                        label = { Text("Your Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            scope.launch {
                                preferencesRepository.saveUserName(nameInput)
                                nameInput = ""
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = nameInput.isNotBlank()
                    ) {
                        Text("Save name")
                    }
                }
            }

            AppCard(containerColor = MaterialTheme.colorScheme.tertiaryContainer) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Dark Mode", fontWeight = FontWeight.Medium)

                    Switch(
                        checked = isDarkMode,
                        onCheckedChange = { newValue ->
                            scope.launch {
                                preferencesRepository.setDarkMode(newValue)
                            }
                        }
                    )

                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = {
                    scope.launch {
                        preferencesRepository.incrementVisitCount()
                    }
                }, modifier = Modifier.weight(1f)) {
                    Text("+ Visit")
                }

                Button(
                    onClick = {
                        scope.launch {
                            preferencesRepository.clearAllPreferences()
                        }
                    }, modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Clear all")
                }
            }
        }
    }
}










