package com.example.weatherhub.domain

import com.example.weatherhub.data.model.WeeklyForecastResponse
import com.example.weatherhub.features.seven_day_forecast.domain.model.SevenDayForecast
import com.example.weatherhub.features.today_weather.domain.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getCityWeather(cityName: String): Flow<Weather>

    fun getSevenDayForecast(cityName: String): Flow<SevenDayForecast>

    fun getLastSearchedCity(): Flow<String?>
}