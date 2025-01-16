package com.example.weatherhub.domain

import com.example.weatherhub.data.data.model.WeatherResponse
import com.example.weatherhub.data.data.model.WeeklyForecastResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    // fixme: add the domain data class
    fun getCityWeather(cityName: String): Flow<WeatherResponse>

    fun getSevenDayForecast(cityName: String): Flow<WeeklyForecastResponse>
}