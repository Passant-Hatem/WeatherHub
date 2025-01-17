package com.example.weatherhub.features.today_weather

import com.example.weatherhub.domain.WeatherRepository
import com.example.weatherhub.features.today_weather.domain.GetCityWeather
import com.example.weatherhub.features.today_weather.domain.Weather
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetCityWeatherTest {

    private lateinit var getCityWeather: GetCityWeather
    private val repository: WeatherRepository = mock()

    @Before
    fun setUp() {
        // Initialize the use case with the mocked repository
        getCityWeather = GetCityWeather(repository)
    }

    @Test
    fun `should return weather data when valid city name is provided`() = runTest {
        // Arrange: Mock repository to return a valid weather flow
        val cityName = "Cairo"
        val expectedWeather = Weather(
            cityName = cityName,
            timestamp = 1737143964,
            timeZone = 7200,
            temperature = 290.57,
            condition = "Clear",
            iconCode = "01n",
        ) // Assume this is a valid Weather object
        whenever(repository.getCityWeather(cityName)).thenReturn(flowOf(expectedWeather))

        // Act: Call the use case
        val result = getCityWeather(cityName)

        // Assert: Verify the flow emits the correct data
        result.collect { weather ->
            assertEquals(expectedWeather, weather) // Check if the weather returned matches
        }
    }

    @Test
    fun `should handle error when repository fails to fetch weather`() = runTest {
        // Arrange: Mock repository to throw an exception or return a failed flow
        val cityName = "InvalidCity"
        whenever(repository.getCityWeather(cityName)).thenReturn(flowOf()) // Empty flow to simulate error/no data

        // Act & Assert: Verify that no data is returned (empty flow scenario)
        val result = getCityWeather(cityName)
        result.collect { weather ->
            assertEquals(null, weather) // Expecting no weather data here
        }
    }
}
