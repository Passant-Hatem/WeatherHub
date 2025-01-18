package com.example.weather_utils

object TemperatureFormatter {
    fun kelvinToCelsius(kelvin: Double): String {
        val temperatureInCelsius = kelvin - 273.15
        return " ${"%.2f".format(temperatureInCelsius)}°C"
    }
}
