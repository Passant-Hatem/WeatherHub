package com.example.weatherhub.core.presentaion.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.weatherhub.core.presentaion.components.AppbarPlaceHolder

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF87CEEB), // Soft blue
    secondary = Color(0xFFFFD700), // Gentle yellow
    tertiary = Color(0xFF98FB98), // Soft green
    background = Color(0xFFFFFFF0), // Off-white
    surface = Color(0xFFFFFFFF), // White
    onPrimary = Color(0xFF003366), // Navy blue
    onSecondary = Color(0xFFFF8C00), // Dark orange
    onTertiary = Color(0xFF228B22) // Forest green
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1E3A8A), // Deep navy
    secondary = Color(0xFFFFB74D), // Muted amber
    tertiary = Color(0xFF008080), // Teal blue
    background = Color(0xFF121212), // Charcoal gray
    surface = Color(0xFF1F1F1F), // Dark gray
    onPrimary = Color(0xFFD1D5DB), // Light gray
    onSecondary = Color(0xFFFFD700), // Golden yellow
    onTertiary = Color(0xFFE0FFFF) // Light cyan
)



@Composable
fun WeatherHubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun WeatherHubScaffold(
    modifier: Modifier = Modifier,
    containerColor: Color? = null,
    appBar: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    WeatherHubTheme {
        Scaffold(
            topBar = appBar ?: { AppbarPlaceHolder() },
            content = { paddingVals ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingVals)
                ) {
                    content()
                }
            },
            modifier = modifier,
            containerColor = containerColor ?: MaterialTheme.colorScheme.background,
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        )
    }
}
