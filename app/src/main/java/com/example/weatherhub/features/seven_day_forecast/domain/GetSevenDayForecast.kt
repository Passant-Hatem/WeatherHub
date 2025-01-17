package com.example.weatherhub.features.seven_day_forecast.domain

import com.example.weatherhub.domain.WeatherRepository
import com.example.weatherhub.features.seven_day_forecast.domain.model.SevenDayForecast
import com.example.weatherhub.features.today_weather.domain.Weather
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSevenDayForecast @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun  invoke(cityName: String): Flow<SevenDayForecast> {
        return repository.getSevenDayForecast(cityName)
    }
}