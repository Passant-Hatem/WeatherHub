package com.example.weatherhub.domain

import com.example.weatherhub.features.seven_day_forecast.domain.model.SevenDayForecast
import com.example.weatherhub.features.today_weather.domain.Weather
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class WeatherRepositoryTest {

    private lateinit var repository: WeatherRepository

    @Before
    fun setup() {
        repository = mock(WeatherRepository::class.java)
    }

    @Test
    fun `getCityWeather should emit correct weather data`() = runTest {
        // Mock data
        val cityName = "Hong Kong"
        val weather = Weather(
            cityName = cityName,
            timestamp = 1737143964,
            timeZone = 7200,
            temperature = 290.57,
            condition = "Clear",
            iconCode = "01n",
        )

        `when`(repository.getCityWeather(cityName)).thenReturn(flow { emit(weather) })

        val result = repository.getCityWeather(cityName).toList()

        assertEquals(1, result.size)
        assertEquals(weather, result.first())
    }

    @Test
    fun `getSevenDayForecast should emit correct forecast data`() = runTest {
        val cityName = "Cairo"
        val forecast = SevenDayForecast(
            cityName,
            listOf(
                Weather(
                    cityName = cityName,
                    timestamp = 1737143964,
                    timeZone = 7200,
                    temperature = 290.57,
                    condition = "Clear",
                    iconCode = "01n",
                ), Weather(
                    cityName = cityName,
                    timestamp = 1737143964,
                    timeZone = 7200,
                    temperature = 290.57,
                    condition = "Clear",
                    iconCode = "01n",
                )
            )
        )

        `when`(repository.getSevenDayForecast(cityName)).thenReturn(flow { emit(forecast) })

        val result = repository.getSevenDayForecast(cityName).toList()

        assertEquals(1, result.size)
        assertEquals(forecast, result.first())
    }

    @Test
    fun `getLastSearchedCity should emit last searched city`() = runTest {
        val lastSearchedCity = "Alexandria"

        `when`(repository.getLastSearchedCity()).thenReturn(flow { emit(lastSearchedCity) })

        val result = repository.getLastSearchedCity().toList()

        assertEquals(1, result.size)
        assertEquals(lastSearchedCity, result.first())
    }

    @Test
    fun `getLastSearchedCity should emit null if no city is searched`() = runTest {

        `when`(repository.getLastSearchedCity()).thenReturn(flow { emit(null) })


        val result = repository.getLastSearchedCity().toList()


        assertEquals(1, result.size)
        assertEquals(null, result.first())
    }
}
