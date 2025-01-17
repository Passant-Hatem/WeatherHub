package com.example.weatherhub.features.today_weather.domain


data class Weather(
    val timestamp: Long,
    val timeZone: Int,
    val cityName: String,
    val temperature: Double,
    val condition: String,
    val iconCode: String
)
