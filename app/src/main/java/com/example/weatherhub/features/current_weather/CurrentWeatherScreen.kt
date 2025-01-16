package com.example.weatherhub.features.current_weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Composable
fun CurrentWeatherScreen() {
    Box(Modifier.fillMaxSize()) {
        Text(text = "CurrentWeatherScreen", modifier = Modifier.align(Alignment.Center))
    }
}

@Serializable
object CurrentWeatherScreenRoute