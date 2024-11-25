package com.qasim.zaka.screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qasim.zaka.viewmodel.WeatherViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavController, viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()

    Scaffold(
        topBar = { WeatherToolbar(navController) },
        bottomBar = { BottomNavigation(navController) },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Search Bar
                var searchQuery by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search Location") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Button(
                    onClick = { viewModel.fetchWeather(searchQuery) },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Search")
                }

                Spacer(modifier = Modifier.height(16.dp))

                when {
                    weatherData != null -> WeatherCard(
                        weatherData = weatherData!!, // Safe cast because of null check
                        isFavorite = viewModel.favorites.value.contains(weatherData),
                        onFavoriteChange = { isFavorite ->
                            viewModel.toggleFavorite(weatherData!!, isFavorite)
                        }
                    )
                }
            }
        }
    )
}

