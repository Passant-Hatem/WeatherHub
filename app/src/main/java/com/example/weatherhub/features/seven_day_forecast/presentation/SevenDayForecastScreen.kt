package com.example.weatherhub.features.seven_day_forecast.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.serialization.Serializable

@Composable
fun SevenDayForecastScreen(
    city: String
) {
    val viewModel: SevenDayForecastViewModel = hiltViewModel()
    Box(Modifier.fillMaxSize()) {
        Button(
            onClick = { viewModel.getWeather(city) },
            modifier = Modifier.align(Alignment.Center)
        ) { Text(text = "Get SevenDayForecastScreen $city") }
    }
}

@Serializable
data class SevenDayForecastScreenRoute(
    val city: String
)