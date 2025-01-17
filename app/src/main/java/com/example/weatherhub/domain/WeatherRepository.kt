package com.example.weatherhub.domain

import com.example.weatherhub.data.model.WeeklyForecastResponse
import com.example.weatherhub.features.today_weather.domain.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getCityWeather(cityName: String): Flow<Weather>

    fun getSevenDayForecast(cityName: String): Flow<WeeklyForecastResponse>

    fun getLastSearchedCity(): Flow<String?>
}