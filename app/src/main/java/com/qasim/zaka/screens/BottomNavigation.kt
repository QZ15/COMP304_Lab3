package com.qasim.zaka.screens

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.qasim.zaka.R

@Composable
fun BottomNavigation(navController: NavController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("home") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home), // Replace with your icon
                    contentDescription = "Home",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Home", style = MaterialTheme.typography.labelMedium) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("favorites") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorites), // Replace with your icon
                    contentDescription = "Favorites",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = { Text("Favorites", style = MaterialTheme.typography.labelMedium) }
        )
    }
}
