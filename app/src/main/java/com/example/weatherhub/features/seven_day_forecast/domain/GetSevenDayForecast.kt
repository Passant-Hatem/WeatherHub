package com.example.weatherhub.features.seven_day_forecast.domain

import com.example.weatherhub.domain.WeatherRepository
import javax.inject.Inject

class GetSevenDayForecast @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun  invoke(cityName: String) = repository.getSevenDayForecast(cityName)
}