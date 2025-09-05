package com.app.integracioncomunitariaapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.integracioncomunitariaapp.model.Postulation
import com.app.integracioncomunitariaapp.ui.viewmodel.PostulationViewModel

@Composable
fun PostulationScreen(viewModel: PostulationViewModel = viewModel()) {
    val postulations by viewModel.postulations.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // Example usage: Fetch postulations for a petition with ID 1
    // In a real app, you would get this ID from navigation arguments or user input
    LaunchedEffect(Unit) {
        viewModel.getPostulationsByPetition(1)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (error != null) {
            Text(
                text = error!!,
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.error
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(postulations) { postulation ->
                    PostulationItem(postulation = postulation)
                }
            }
        }
    }
}

@Composable
fun PostulationItem(postulation: Postulation) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Postulation ID: ${postulation.id}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Petition ID: ${postulation.idPetition}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Provider ID: ${postulation.idProvider}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "State ID: ${postulation.idState}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}