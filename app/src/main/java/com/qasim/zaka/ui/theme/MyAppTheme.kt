package com.qasim.zaka.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Dark theme color scheme
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1E88E5),       // Blue for primary elements
    onPrimary = Color.White,           // White text on primary elements
    background = Color(0xFF121212),    // Dark background
    onBackground = Color.White,        // White text on the background
    surface = Color(0xFF1C1C1C),       // Darker gray for surfaces
    onSurface = Color.White            // White text on surfaces
)

// Light theme color scheme
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1976D2),       // Blue for primary elements
    onPrimary = Color.White,           // White text on primary elements
    background = Color(0xFFFFFFFF),    // Light background
    onBackground = Color.Black,        // Black text on the background
    surface = Color(0xFFFAFAFA),       // Light gray for surfaces
    onSurface = Color.Black            // Black text on surfaces
)

// App Theme
@Composable
fun MyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Use system theme by default
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
