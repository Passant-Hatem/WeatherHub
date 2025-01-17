package com.example.weatherhub.data.mapper

import com.example.weatherhub.data.model.TodayWeatherDataResponse
import com.example.weatherhub.features.today_weather.domain.Weather

fun TodayWeatherDataResponse.toDomain(): Weather {
    // todo: make sure to handle this exception
    val weatherInfo =
        weatherResponseDetails.firstOrNull() ?: throw IllegalStateException("Weather data missing")
    return Weather(
        timestamp = timestamp,
        timeZone = timezoneOffset,
        cityName = cityName,
        temperature = mainWeatherInfo.temperature,
        condition = weatherResponseDetails.first().condition,
        iconCode = weatherInfo.iconCode
    )
}


