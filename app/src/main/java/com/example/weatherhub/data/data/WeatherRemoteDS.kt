package com.example.weatherhub.data.data

import com.example.weatherhub.BuildConfig
import com.example.weatherhub.data.data.model.WeatherResponse
import com.example.weatherhub.data.data.model.WeeklyForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRemoteDS {

    @GET("weather")
    suspend fun getCityWeather(
        @Query("q") cityName: String
    ): WeatherResponse

    @GET("forecast")
    suspend fun getWeeklyForecast(
        @Query("q") cityName: String,
        @Query("cnt") daysCount: Int
//        @Query("units") units: String = "metric" // Units of measurement (default is metric)
    ): WeeklyForecastResponse


}