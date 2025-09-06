package com.app.integracioncomunitariaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.integracioncomunitariaapp.ui.petition.PetitionScreen
import com.app.integracioncomunitariaapp.ui.postulation.PostulationScreen
import com.app.integracioncomunitariaapp.ui.theme.IntegracionComunitariaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntegracionComunitariaAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "petition") {
                        composable("petition") { PetitionScreen(navController) }
                        composable("postulation/{petitionId}") { backStackEntry ->
                            val petitionId = backStackEntry.arguments?.getString("petitionId")
                            PostulationScreen(petitionId = petitionId ?: "")
                        }
                    }
                }
            }
        }
    }
}
