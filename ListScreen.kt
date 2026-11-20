package com.example.practica12.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private val FondoOscuro = Color(0xFF0F1117)
private val TarjetaOscura = Color(0xFF1A1D26)
private val TextoClaro = Color(0xFFF3F4F6)
private val AcentoAzul = Color(0xFF3B82F6)

@Composable
fun TiposScreen(viewModel: PokeViewModel, onTypeClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoOscuro)
            .padding(vertical = 8.dp)
    ) {
        items(viewModel.tipos.value) { tipo ->
            ListItemPoke(
                texto = tipo.name.uppercase(),
                onClick = { onTypeClick(tipo.id) }
            )
        }
    }
}

@Composable
fun PokemonsByTypeScreen(
    viewModel: PokeViewModel,
    typeId: String,
    onPokeClick: (String) -> Unit
) {
    LaunchedEffect(typeId) {
        viewModel.cargarPokemonsPorTipo(typeId)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoOscuro)
            .padding(vertical = 8.dp)
    ) {
        items(viewModel.pokemonsPorTipo.value) { poke ->
            ListItemPoke(
                texto = "#${poke.id} - ${poke.name.uppercase()}",
                onClick = { onPokeClick(poke.id) }
            )
        }
    }
}

@Composable
fun ListItemPoke(texto: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = TarjetaOscura),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color(0xFFEF4444), CircleShape)
                    .border(2.dp, AcentoAzul, CircleShape)
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(TextoClaro, CircleShape)
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = texto,
                style = MaterialTheme.typography.titleMedium,
                color = TextoClaro,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
