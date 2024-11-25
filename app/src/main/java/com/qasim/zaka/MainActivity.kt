package com.qasim.zaka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.qasim.zaka.data.WeatherData
import com.qasim.zaka.data.WeatherDatabase
import com.qasim.zaka.data.WeatherRepository
import com.qasim.zaka.screens.FavoritesScreen
import com.qasim.zaka.screens.HomeScreen
import com.qasim.zaka.navigation.Routes
import com.qasim.zaka.network.RetrofitInstance
import com.qasim.zaka.screens.WeatherCard
import com.qasim.zaka.ui.theme.MyAppTheme
import com.qasim.zaka.viewmodel.WeatherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up database and repository
        val database = Room.databaseBuilder(
            applicationContext,
            WeatherDatabase::class.java,
            "weather_database"
        ).build()

        val weatherApi = RetrofitInstance.api
        val repository = WeatherRepository(weatherApi, database.locationDao())

        // Create ViewModel
        val viewModel = WeatherViewModel(repository)

        setContent {
            MyAppTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.HOME
                ) {
                    // Home Screen
                    composable(Routes.HOME) {
                        HomeScreen(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }

                    // Favorites Screen
                    composable(Routes.FAVORITES) {
                        // Simulate loading favorites; replace with actual stored data
                        val favorites = listOf(
                            WeatherData(
                                name = "Lahore",
                                temperature = "25°C",
                                humidity = "60%",
                                weatherCondition = "Sunny",
                                detailedInfo = "Wind Speed: 10 km/h\nPressure: 1013 hPa\nVisibility: 10 km"
                            ),
                            WeatherData(
                                name = "Rome",
                                temperature = "18°C",
                                humidity = "70%",
                                weatherCondition = "Rainy",
                                detailedInfo = "Wind Speed: 5 km/h\nPressure: 1005 hPa\nVisibility: 8 km"
                            )
                        )
                        FavoritesScreen(
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}
