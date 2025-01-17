package com.example.weatherhub.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodayWeatherDataResponse(
    @SerialName("coord")
    val coordinates: Coordinates,

    @SerialName("weather")
    val weatherResponseDetails: List<WeatherResponse>,

    @SerialName("base")
    val dataSource: String,

    @SerialName("main")
    val mainWeatherInfo: MainWeatherInfo,

    @SerialName("visibility")
    val visibilityDistance: Int,

    @SerialName("wind")
    val windInfo: Wind,

    @SerialName("clouds")
    val cloudCoverage: Clouds,

    @SerialName("dt")
    val timestamp: Long,

    @SerialName("sys")
    val systemInfo: SystemInfo,

    @SerialName("timezone")
    val timezoneOffset: Int,

    @SerialName("id")
    val cityId: Int,

    @SerialName("name")
    val cityName: String,

    @SerialName("cod")
    val statusCode: Int
)

@Serializable
data class WeatherResponse(
    @SerialName("id")
    val conditionId: Int,
    @SerialName("main")
    val condition: String,
    @SerialName("description")
    val conditionDescription: String,
    @SerialName("icon")
    val iconCode: String
)

@Serializable
data class MainWeatherInfo(
    @SerialName("temp")
    val temperature: Double,
    @SerialName("feels_like")
    val feelsLike: Double,
    @SerialName("temp_min")
    val minTemperature: Double,
    @SerialName("temp_max")
    val maxTemperature: Double,
    @SerialName("pressure")
    val pressure: Int,
    @SerialName("humidity")
    val humidity: Int,
    @SerialName("sea_level")
    val seaLevelPressure: Int? = null,
    @SerialName("grnd_level")
    val groundLevelPressure: Int? = null
)

@Serializable
data class Wind(
    @SerialName("speed")
    val speed: Double,
    @SerialName("deg")
    val directionDegrees: Int
)

@Serializable
data class Clouds(
    @SerialName("all")
    val cloudinessPercentage: Int
)

@Serializable
data class SystemInfo(
    @SerialName("type")
    val systemType: Int? = null,
    @SerialName("id")
    val systemId: Int? = null,
    @SerialName("country")
    val countryCode: String,
    @SerialName("sunrise")
    val sunriseTimestamp: Long,
    @SerialName("sunset")
    val sunsetTimestamp: Long
)
