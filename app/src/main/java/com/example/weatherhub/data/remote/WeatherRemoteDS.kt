package com.example.weatherhub.data.remote

import com.example.weatherhub.data.model.TodayWeatherDataResponse
import com.example.weatherhub.data.model.WeeklyForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRemoteDS {

    @GET("weather")
    suspend fun getCityWeather(
        @Query("q") cityName: String
    ): TodayWeatherDataResponse

    @GET("forecast")
    suspend fun getWeeklyForecast(
        @Query("q") cityName: String,
        @Query("cnt") daysCount: Int
    ): WeeklyForecastResponse


}