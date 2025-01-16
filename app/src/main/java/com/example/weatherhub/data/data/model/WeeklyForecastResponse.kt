package com.example.weatherhub.data.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeeklyForecastResponse(
    @SerialName("cod")
    val statusCode: String,
    @SerialName("message")
    val message: Int,
    @SerialName("cnt")
    val count: Int,
    @SerialName("list")
    val forecasts: List<Forecast>,
    @SerialName("city")
    val city: City
)

@Serializable
data class Forecast(
    @SerialName("dt")
    val timestamp: Long,
    @SerialName("main")
    val main: MainWeatherDetails,
    @SerialName("weather")
    val weather: List<WeatherCondition>,
    @SerialName("clouds")
    val clouds: CloudDetails,
    @SerialName("wind")
    val wind: WindDetails,
    @SerialName("visibility")
    val visibility: Int,
    @SerialName("pop")
    val precipitationProbability: Float,
    @SerialName("sys")
    val systemDetails: SystemDetails,
    @SerialName("dt_txt")
    val dateTimeText: String
)

@Serializable
data class MainWeatherDetails(
    @SerialName("temp")
    val temperature: Float,
    @SerialName("feels_like")
    val feelsLike: Float,
    @SerialName("temp_min")
    val minimumTemperature: Float,
    @SerialName("temp_max")
    val maximumTemperature: Float,
    @SerialName("pressure")
    val pressure: Int,
    @SerialName("sea_level")
    val seaLevelPressure: Int,
    @SerialName("grnd_level")
    val groundLevelPressure: Int,
    @SerialName("humidity")
    val humidity: Int,
    @SerialName("temp_kf")
    val temperatureAdjustmentFactor: Float
)

@Serializable
data class WeatherCondition(
    @SerialName("id")
    val conditionId: Int,
    @SerialName("main")
    val condition: String,
    @SerialName("description")
    val description: String,
    @SerialName("icon")
    val iconCode: String
)

@Serializable
data class CloudDetails(
    @SerialName("all")
    val cloudiness: Int
)

@Serializable
data class WindDetails(
    @SerialName("speed")
    val speed: Float,
    @SerialName("deg")
    val direction: Int,
    @SerialName("gust")
    val gustSpeed: Float
)

@Serializable
data class SystemDetails(
    @SerialName("pod")
    val partOfDay: String
)

@Serializable
data class City(
    @SerialName("id")
    val cityId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("coord")
    val coordinates: Coordinates,
    @SerialName("country")
    val country: String,
    @SerialName("population")
    val population: Int,
    @SerialName("timezone")
    val timezoneOffset: Int,
    @SerialName("sunrise")
    val sunriseTimestamp: Long,
    @SerialName("sunset")
    val sunsetTimestamp: Long
)
