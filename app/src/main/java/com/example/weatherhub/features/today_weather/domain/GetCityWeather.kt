package com.example.weatherhub.features.today_weather.domain

import com.example.weatherhub.domain.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCityWeather @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(cityName: String): Flow<Weather> {
        return repository.getCityWeather(cityName)
    }
}