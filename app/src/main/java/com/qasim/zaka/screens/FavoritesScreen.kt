package com.qasim.zaka.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qasim.zaka.data.WeatherData
import com.qasim.zaka.viewmodel.WeatherViewModel

@Composable
fun FavoritesScreen(navController: NavController, viewModel: WeatherViewModel) {
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
                // Title for the Favorites Screen
                Text(
                    text = "Favorites",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                val favorites by viewModel.favorites.collectAsState()

                LazyColumn(
                    //modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    //contentPadding = PaddingValues(16.dp),
                ) {
                    items(favorites) { weatherData ->
                        WeatherCard(
                            weatherData = weatherData,
                            isFavorite = true, // Always true for favorites
                            onFavoriteChange = { isFavorite ->
                                viewModel.toggleFavorite(weatherData, isFavorite)
                            }
                        )
                    }
                }
            }
        }
    )
}
