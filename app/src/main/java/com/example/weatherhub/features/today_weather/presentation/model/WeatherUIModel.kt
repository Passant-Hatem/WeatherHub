package com.example.weatherhub.features.today_weather.presentation.model

import com.example.weather_utils.TemperatureFormatter.kelvinToCelsius
import com.example.weather_utils.TimeFormatter.convertToLocalTime
import com.example.weatherhub.core.presentaion.model.WeatherIconProvider.getIconURL
import com.example.weatherhub.features.today_weather.domain.Weather

data class WeatherUIModel(
    val dataAndTime: String,
    val cityName: String,
    val temperature: String,
    val condition: String,
    val iconUrl: String
)

fun Weather.toUIModel(): WeatherUIModel{
    return WeatherUIModel(
        dataAndTime = convertToLocalTime(timestamp, timeZone),
        cityName = cityName,
        temperature = kelvinToCelsius(temperature),
        condition = condition,
        iconUrl = getIconURL(iconCode)
    )
}