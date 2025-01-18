package com.example.weatherhub.features.today_weather.presentation.model


import com.example.weather_utils.TemperatureFormatter
import com.example.weather_utils.TimeFormatter
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
        // if u r using the library comment these out
//        dataAndTime = convertToLocalTime(timestamp, timeZone),
//        temperature = kelvinToCelsius(temperature),

        dataAndTime = TimeFormatter.convertToLocalTime(timestamp, timeZone),
        cityName = cityName,
        temperature = TemperatureFormatter.kelvinToCelsius(temperature),
        condition = condition,
        iconUrl = getIconURL(iconCode)
    )
}