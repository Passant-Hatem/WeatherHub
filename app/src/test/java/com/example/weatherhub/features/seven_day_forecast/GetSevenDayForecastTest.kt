package com.example.weatherhub.features.seven_day_forecast

import com.example.weatherhub.domain.WeatherRepository
import com.example.weatherhub.features.seven_day_forecast.domain.GetSevenDayForecast
import com.example.weatherhub.features.seven_day_forecast.domain.model.SevenDayForecast
import com.example.weatherhub.features.today_weather.domain.GetCityWeather
import com.example.weatherhub.features.today_weather.domain.Weather
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
class GetSevenDayForecastTest {

    private lateinit var getSevenDayForecast: GetSevenDayForecast
    private val repository: WeatherRepository = mock()

    @Before
    fun setUp() {
        getSevenDayForecast = GetSevenDayForecast(repository)
    }

    @Test
    fun `should return forecast data when valid city name is provided`() = runTest {
        val cityName = "Cairo"
        val expectedForecast = SevenDayForecast(
            cityName = cityName,
            weather = listOf(
                Weather(
                    timestamp = 1737143964,
                    timeZone = 7200,
                    cityName = cityName,
                    temperature = 290.57,
                    condition = "Clear",
                    iconCode = "01n"
                ),
                Weather(
                    timestamp = 1737143964,
                    timeZone = 7200,
                    cityName = cityName,
                    temperature = 390.57,
                    condition = "Sunny",
                    iconCode = "02n"
                ),
                Weather(
                    timestamp = 1737230364,
                    timeZone = 7200,
                    cityName = cityName,
                    temperature = 292.15,
                    condition = "Cloudy",
                    iconCode = "03n"
                )
            )
        )
        whenever(repository.getSevenDayForecast(cityName)).thenReturn(flowOf(expectedForecast))

        val result = getSevenDayForecast(cityName)

        result.collect { forecast ->
            assertEquals(expectedForecast, forecast)
        }
    }

    @Test
    fun `should handle error when repository fails to fetch forecast`() = runTest {
        val cityName = "InvalidCity"
        val exception = Exception("Network error")
        whenever(repository.getSevenDayForecast(cityName)).thenReturn(flow {
            throw exception
        })

        val result = getSevenDayForecast(cityName)
        try {
            result.collect{ }
            fail("Expected an exception to be thrown")
        } catch (e: Exception) {
            assertEquals(exception.message, e.message)
        }
    }

    @Test
    fun `should return empty forecast when no data is available`() = runTest {
        val cityName = "NoDataCity"
        val emptyForecast = SevenDayForecast(cityName = cityName, weather = emptyList())
        whenever(repository.getSevenDayForecast(cityName)).thenReturn(flowOf(emptyForecast))

        val result = getSevenDayForecast(cityName)

        result.collect { forecast ->
            assertEquals(emptyForecast, forecast)
        }
    }
}
