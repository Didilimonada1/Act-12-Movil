package com.example.practica12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica12.presentation.*

class MainActivity : ComponentActivity() {
    private val viewModel: PokeViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val fondoPrincipal = Color(0xFF0F1117)
            val barraSuperior = Color(0xFF151821)
            val textoClaro = Color(0xFFF3F4F6)

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = fondoPrincipal,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Pokédex API",
                                color = textoClaro,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = barraSuperior
                        )
                    )
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "tipos",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable("tipos") {
                        TiposScreen(viewModel) { id ->
                            navController.navigate("pokemons/$id")
                        }
                    }

                    composable("pokemons/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id") ?: ""
                        PokemonsByTypeScreen(viewModel, id) { pokeId ->
                            navController.navigate("detalle/$pokeId")
                        }
                    }

                    composable("detalle/{id}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id") ?: ""
                        PokemonDetailScreen(viewModel, id)
                    }
                }
            }
        }
    }
}
