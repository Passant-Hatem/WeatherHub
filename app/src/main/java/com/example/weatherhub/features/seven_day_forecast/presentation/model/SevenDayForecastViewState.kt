package com.example.weatherhub.features.seven_day_forecast.presentation.model

data class SevenDayForecastViewState(
    val state: SevenDayForecastState = SevenDayForecastState.Loading,
    val effect: SevenDayForecastEffect? = null
)
