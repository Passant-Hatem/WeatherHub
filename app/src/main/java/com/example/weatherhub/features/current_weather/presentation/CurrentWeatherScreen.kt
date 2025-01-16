package com.example.weatherhub.features.current_weather.presentation

import androidx.compose.foundation.clickable
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherhub.core.mics.Consumer
import kotlinx.serialization.Serializable

@Composable
fun CurrentWeatherScreen(
    onSeeSevenDayForecast: Consumer<String>
) {
    val viewModel: CurrentWeatherViewModel = hiltViewModel()
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            viewModel.getWeather()
        }) {
            Text(text = "Get Weather")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "CurrentWeatherScreen", modifier = Modifier
            .clickable { onSeeSevenDayForecast("cairo") })
    }
}

@Serializable
object CurrentWeatherScreenRoute