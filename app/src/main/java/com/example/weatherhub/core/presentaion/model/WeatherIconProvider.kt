package com.example.weatherhub.core.presentaion.model

object WeatherIconProvider {
    fun getIconURL(iconCode: String): String =
        "https://openweathermap.org/img/wn/${iconCode}@2x.png"
}