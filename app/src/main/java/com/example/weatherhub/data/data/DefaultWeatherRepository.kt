package com.example.weatherhub.data.data

import com.example.weatherhub.data.data.model.WeatherResponse
import com.example.weatherhub.data.data.model.WeeklyForecastResponse
import com.example.weatherhub.domain.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    private val remoteDS: WeatherRemoteDS
) : WeatherRepository {
    override fun getCityWeather(cityName: String): Flow<WeatherResponse> =
        flow { emit(remoteDS.getCityWeather(cityName)) }

    override fun getSevenDayForecast(cityName: String): Flow<WeeklyForecastResponse> = flow {
        emit(remoteDS.getWeeklyForecast(cityName, SEVEN_DAYS))
    }

    private companion object {
        const val SEVEN_DAYS = 7
    }
}