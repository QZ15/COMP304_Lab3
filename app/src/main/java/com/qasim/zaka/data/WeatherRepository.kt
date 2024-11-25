package com.qasim.zaka.data

import com.qasim.zaka.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(
    private val api: WeatherApi,
    private val dao: LocationDao
) {
    // Fetch weather data from the API
    suspend fun fetchWeather(location: String): WeatherResponse {
        return api.getWeather(location, API_KEY)
    }

    // Save a location to the database
    suspend fun saveLocation(location: Location) {
        return withContext(Dispatchers.IO) {
            dao.insert(location)
        }
    }

    // Get all saved locations from the database
    suspend fun getAllLocations(): List<Location> {
        return withContext(Dispatchers.IO) {
            dao.getAllLocations()
        }
    }

    // Delete a location from the database
    suspend fun deleteLocation(location: Location) {
        return withContext(Dispatchers.IO) {
            dao.delete(location)
        }
    }

    companion object {
        private const val API_KEY = "67ddb3b583547c28a3d91903bf2192fc"
    }
}
