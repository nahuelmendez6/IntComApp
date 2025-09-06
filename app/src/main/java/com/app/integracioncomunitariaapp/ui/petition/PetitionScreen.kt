package com.app.integracioncomunitariaapp.ui.petition

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.app.integracioncomunitariaapp.model.Petition
import com.app.integracioncomunitariaapp.network.RetrofitClient
import com.app.integracioncomunitariaapp.repository.PetitionRepository
import com.app.integracioncomunitariaapp.repository.PostulationRepository
import com.app.integracioncomunitariaapp.ui.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetitionScreen(navController: NavController, viewModel: PetitionViewModel = viewModel(factory = ViewModelFactory(PetitionRepository(RetrofitClient.instance), PostulationRepository(RetrofitClient.instance)))) {
    val petitions by viewModel.petitions.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPetitions()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Petitions") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: Handle postulation publishing */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Publish Postulation")
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (error != null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = error!!)
                }
            } else if (petitions.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(petitions) { petition ->
                        PetitionItem(petition = petition, navController = navController)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun PetitionItem(petition: Petition, navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = petition.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "From: ${formatDate(petition.dateSince)}", style = MaterialTheme.typography.bodySmall)
            Text(text = "To: ${formatDate(petition.dateUntil)}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Button(onClick = { navController.navigate("postulation/${petition.idPetition}") }) {
                    Text("Postularse")
                }
                Button(onClick = { navController.navigate("postulationsList/${petition.idPetition}") }) {
                    Text("Ver Postulaciones")
                }
            }
        }
    }
}

private fun formatDate(date: Date): String {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(date)
}
