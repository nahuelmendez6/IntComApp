package com.app.integracioncomunitariaapp.ui.postulation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostulationScreen(
    petitionId: String,
    viewModel: PostulationViewModel = viewModel(factory = PostulationViewModelFactory())
) {
    var proposal by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Postularse") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = proposal,
                onValueChange = { proposal = it },
                label = { Text("Propuesta de la postulación") },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState !is PostulationUiState.Loading
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = cost,
                onValueChange = { cost = it },
                label = { Text("Costo de la postulación") },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState !is PostulationUiState.Loading,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.createPostulation(petitionId.toLong(), proposal, cost.toDoubleOrNull() ?: 0.0) },
                enabled = uiState !is PostulationUiState.Loading
            ) {
                Text("Enviar Postulación")
            }

            when (val state = uiState) {
                is PostulationUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is PostulationUiState.Success -> {
                    Text("Postulación enviada con éxito")
                }
                is PostulationUiState.Error -> {
                    Text(text = state.message)
                }
                else -> {}
            }
        }
    }
}
