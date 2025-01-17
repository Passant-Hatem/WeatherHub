package com.example.weatherhub.features.seven_day_forecast.presentation.model

import com.example.weatherhub.features.today_weather.domain.Weather


sealed class SevenDayForecastState {
    data object Loading : SevenDayForecastState()
    data class Content(val city: String, val forecast: List<Weather>) : SevenDayForecastState()
    data object Empty : SevenDayForecastState()
}
