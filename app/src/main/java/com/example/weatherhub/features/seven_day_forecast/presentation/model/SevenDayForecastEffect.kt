package com.example.weatherhub.features.seven_day_forecast.presentation.model

sealed class SevenDayForecastEffect {
    data class ShowError(val errorMessage: Int) : SevenDayForecastEffect()
}
