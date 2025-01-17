package com.example.weatherhub

import com.example.weatherhub.domain.WeatherRepository
import com.example.weatherhub.features.today_weather.domain.GetLastSearchedCity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class GetLastSearchedCityTest {

    private lateinit var getLastSearchedCity: GetLastSearchedCity
    private val repository: WeatherRepository = mock()

    @Before
    fun setUp() {
        // Initialize the use case with the mocked repository
        getLastSearchedCity = GetLastSearchedCity(repository)
    }

    @Test
    fun `should return last searched city when available`() = runTest {
        // Arrange: Mock repository to return a valid city name
        val lastSearchedCity = "Paris"
        whenever(repository.getLastSearchedCity()).thenReturn(flowOf(lastSearchedCity))

        // Act: Call the use case
        val result = getLastSearchedCity()

        // Assert: Verify the flow emits the correct city name
        result.collect { cityName ->
            assertEquals(lastSearchedCity, cityName) // Check if the returned city matches
        }
    }

    @Test
    fun `should return null when no last searched city is available`() = runTest {
        // Arrange: Mock repository to return null (no city)
        whenever(repository.getLastSearchedCity()).thenReturn(flowOf(null))

        // Act: Call the use case
        val result = getLastSearchedCity()

        // Assert: Verify the flow emits null
        result.collect { cityName ->
            assertNull(cityName) // Expecting null when no city is stored
        }
    }
}
