package com.app.integracioncomunitariaapp.ui.postulation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.integracioncomunitariaapp.network.RetrofitClient
import com.app.integracioncomunitariaapp.repository.PetitionRepository
import com.app.integracioncomunitariaapp.repository.PostulationRepository
import com.app.integracioncomunitariaapp.ui.viewmodel.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostulationScreen(
    petitionId: String,
    viewModel: PostulationViewModel = viewModel(
        factory = ViewModelFactory(
            PetitionRepository(RetrofitClient.instance),
            PostulationRepository(RetrofitClient.instance)
        )
    )
) {
    var content by remember { mutableStateOf("") }
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
                value = content,
                onValueChange = { content = it },
                label = { Text("Contenido de la postulación") },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState !is PostulationUiState.Loading
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.createPostulation(petitionId.toLong(), content) },
                enabled = uiState !is PostulationUiState.Loading
            ) {
                Text("Enviar Postulación")
            }

            when (uiState) {
                is PostulationUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is PostulationUiState.Success -> {
                    Text("Postulación enviada con éxito")
                }
                is PostulationUiState.Error -> {
                    Text(text = (uiState as PostulationUiState.Error).message)
                }
                else -> {}
            }
        }
    }
}
