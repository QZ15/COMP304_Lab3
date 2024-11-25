package com.qasim.zaka.data

data class WeatherResponse(
    val weather: List<Weather>, // List of weather conditions
    val main: Main,             // Temperature and humidity
    val wind: Wind,             // Wind speed
    val name: String,           // City name
    val visibility: Int         // Visibility in meters
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String // Icon code for weather condition
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
)

data class Wind(
    val speed: Double
)