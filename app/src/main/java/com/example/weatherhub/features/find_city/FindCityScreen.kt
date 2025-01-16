package com.example.weatherhub.features.find_city

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherhub.core.mics.Action
import com.example.weatherhub.core.mics.Consumer
import kotlinx.serialization.Serializable

@Composable
fun FindCityScreen(
    onNavigateToSevenDayForecast: Consumer<String>,
    onNavigateToCurrentWeather: Action,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onNavigateToSevenDayForecast("Cairo") }) {
            Text(text = "NavigateToSevenDayForecast")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { onNavigateToCurrentWeather() }) {
            Text(text = "NavigateToCurrentWeather")
        }

    }
}

@Serializable
object FindCityScreenRoute