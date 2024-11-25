package com.qasim.zaka.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qasim.zaka.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherToolbar(navController: NavController) {
    TopAppBar(
        title = { Text("Weather App", style = MaterialTheme.typography.headlineSmall) },
        navigationIcon = {
            IconButton(onClick = { navController.navigate("home") }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_sun), // Replace with your logo resource
                    contentDescription = "Home Logo",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
    )
}
