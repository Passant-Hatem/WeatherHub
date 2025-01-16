package com.example.weatherhub.features.current_weather.domain

import com.example.weatherhub.domain.WeatherRepository
import javax.inject.Inject

class GetCityWeather @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(cityName: String) = repository.getCityWeather(cityName)
}