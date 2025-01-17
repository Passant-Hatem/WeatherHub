package com.example.weatherhub.features.seven_day_forecast.presentation.model

import kotlinx.serialization.Serializable


@Serializable
data class SevenDayForecastScreenRoute(
    val city: String
)