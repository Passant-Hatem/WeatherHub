package com.example.weatherhub.data.local

import com.example.weatherhub.core.data.NonSafeComplexPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherLocalDS @Inject constructor(
    private val pref: NonSafeComplexPreferences
) {
    fun saveLastSearchedCity(cityName: String): Flow<String> = pref.saveItem(cityName, SEARCH_HISTORY)
    fun getLastSearchedCity(): Flow<String?> = pref.getItemOnce(SEARCH_HISTORY,null)

    companion object{
        const val SEARCH_HISTORY = "SEARCH_HISTORY"
    }
}