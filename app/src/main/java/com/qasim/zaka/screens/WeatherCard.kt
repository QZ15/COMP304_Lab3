package com.qasim.zaka.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.qasim.zaka.R
import com.qasim.zaka.data.WeatherResponse


@Composable
fun WeatherCard(
    weatherData: WeatherResponse,
    isFavorite: Boolean,
    onFavoriteChange: (Boolean) -> Unit
) {
    var isFlipped by remember { mutableStateOf(false) }
    val weatherConditionIconUrl = weatherData.weather.getOrNull(0)?.icon?.let {
        "https://openweathermap.org/img/wn/${it}@2x.png"
    } ?: R.drawable.ic_weather_placeholder // Default placeholder if icon is missing

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clickable { isFlipped = !isFlipped }
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isFlipped) {
            // Back of the card: Detailed Information
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Details for ${weatherData.name}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text("Temperature: ${weatherData.main.temp}°C", style = MaterialTheme.typography.bodyLarge)
                Text("Feels Like: ${weatherData.main.feels_like}°C", style = MaterialTheme.typography.bodyLarge)
                Text("Humidity: ${weatherData.main.humidity}%", style = MaterialTheme.typography.bodyLarge)
                Text("Pressure: ${weatherData.main.pressure} hPa", style = MaterialTheme.typography.bodyLarge)
                Text("Wind Speed: ${weatherData.wind.speed} m/s", style = MaterialTheme.typography.bodyLarge)
                Text("Visibility: ${weatherData.visibility / 1000} km", style = MaterialTheme.typography.bodyLarge)
                Text("Conditions: ${weatherData.weather[0].description}", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            // Front of the card: Summary
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = weatherData.name,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Weather icon
                    AsyncImage(
                        model = weatherConditionIconUrl,
                        contentDescription = "Weather Icon",
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Temp: ${weatherData.main.temp}°C",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Humidity: ${weatherData.main.humidity}%",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = weatherData.weather[0].main,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Favorite star in the top-right corner
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            ) {
                IconToggleButton(
                    checked = isFavorite,
                    onCheckedChange = { onFavoriteChange(it) }
                ) {
                    Icon(
                        painter = painterResource(if (isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_outline),
                        contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = if (isFavorite) Color.Yellow else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
