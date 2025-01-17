package com.example.weatherhub.features.today_weather.domain

import com.example.weatherhub.domain.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastSearchedCity  @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(): Flow<String?> {
        return repository.getLastSearchedCity()
    }
}