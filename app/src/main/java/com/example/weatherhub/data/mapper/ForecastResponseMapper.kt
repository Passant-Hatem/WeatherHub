package com.example.weatherhub.data.mapper

import com.example.weatherhub.data.model.WeeklyForecastResponse
import com.example.weatherhub.features.seven_day_forecast.domain.model.SevenDayForecast
import com.example.weatherhub.features.today_weather.domain.Weather


fun WeeklyForecastResponse.toDomain(): SevenDayForecast {
    val weather = forecasts.map { forecast ->
        val weatherInfo =
            forecast.weather.firstOrNull() ?: throw IllegalStateException("Weather data missing")
        Weather(
            timestamp = forecast.timestamp,
            timeZone = city.timezoneOffset,
            cityName = city.name,
            temperature = forecast.main.temperature.toDouble(),
            condition = weatherInfo.condition,
            iconCode = weatherInfo.iconCode
        )
    }
    return SevenDayForecast(
        cityName = city.name,
        weather = weather
    )
}