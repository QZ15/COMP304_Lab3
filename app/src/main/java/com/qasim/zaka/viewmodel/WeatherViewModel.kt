package com.qasim.zaka.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qasim.zaka.data.Location
import com.qasim.zaka.data.WeatherRepository
import com.qasim.zaka.data.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {
    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> get() = _weatherData

    fun fetchWeather(location: String) {
        viewModelScope.launch {
            try {
                val response = repository.fetchWeather(location)
                _weatherData.value = response
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Error fetching weather: ${e.message}")
            }
        }
    }

    private val _favorites = MutableStateFlow<List<WeatherResponse>>(emptyList())
    val favorites: StateFlow<List<WeatherResponse>> get() = _favorites

    fun toggleFavorite(weatherData: WeatherResponse, isFavorite: Boolean) {
        viewModelScope.launch {
            val currentFavorites = _favorites.value.toMutableList()
            if (isFavorite) {
                currentFavorites.add(weatherData)
            } else {
                currentFavorites.remove(weatherData)
            }
            _favorites.value = currentFavorites
        }
    }
}
