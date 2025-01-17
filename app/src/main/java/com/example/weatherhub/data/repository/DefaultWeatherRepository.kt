package com.example.weatherhub.data.repository

import com.example.weatherhub.core.mics.emitFlow
import com.example.weatherhub.core.mics.flatMapSame
import com.example.weatherhub.data.local.WeatherLocalDS
import com.example.weatherhub.data.mapper.toDomain
import com.example.weatherhub.data.remote.WeatherRemoteDS
import com.example.weatherhub.domain.WeatherRepository
import com.example.weatherhub.features.seven_day_forecast.domain.model.SevenDayForecast
import com.example.weatherhub.features.today_weather.domain.Weather
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    private val remoteDS: WeatherRemoteDS,
    private val localDS: WeatherLocalDS
) : WeatherRepository {
    override fun getCityWeather(cityName: String): Flow<Weather> =
        emitFlow { remoteDS.getCityWeather(cityName).toDomain() }.flatMapSame {
            localDS.saveLastSearchedCity(it.cityName)
        }

    override fun getSevenDayForecast(cityName: String): Flow<SevenDayForecast> = emitFlow {
        remoteDS.getWeeklyForecast(cityName, SEVEN_DAYS).toDomain()
    }

    override fun getLastSearchedCity(): Flow<String?> {
        return localDS.getLastSearchedCity()
    }

    private companion object {
        const val SEVEN_DAYS = 7
    }
}