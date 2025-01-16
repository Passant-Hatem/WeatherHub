package com.example.weatherhub.features.seven_day_forecast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Composable
fun SevenDayForecastScreen(
    city: String
) {
    Box(Modifier.fillMaxSize()) {
        Text(text = "SevenDayForecastScreen $city", modifier = Modifier.align(Alignment.Center))
    }
}

@Serializable
data class SevenDayForecastScreenRoute(
    val city: String
)