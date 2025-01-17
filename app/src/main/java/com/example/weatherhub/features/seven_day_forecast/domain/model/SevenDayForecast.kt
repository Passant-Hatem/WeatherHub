package com.example.weatherhub.features.seven_day_forecast.domain.model

import com.example.weatherhub.features.today_weather.domain.Weather

data class SevenDayForecast(
    val cityName: String,
    val weather: List<Weather>
)
