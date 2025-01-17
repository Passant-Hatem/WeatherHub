package com.example.weatherhub.features.seven_day_forecast.presentation.model

sealed class SevenDayForecastIntent {
    data class LoadForecast(val city: String) : SevenDayForecastIntent()
}
