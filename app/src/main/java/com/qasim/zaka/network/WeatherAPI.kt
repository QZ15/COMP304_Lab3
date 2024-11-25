package com.qasim.zaka.network

import com.qasim.zaka.data.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") location: String,
        @Query("appId") apiKey: String,
        @Query("units") units: String = "metric" // Optional: Use metric units
    ): WeatherResponse
}
