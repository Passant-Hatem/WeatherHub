package com.example.weatherhub.features.current_weather.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherhub.features.current_weather.domain.GetCityWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCityWeather: GetCityWeather
) : ViewModel() {
    fun getWeather() {
        getCityWeather("Cairo").onEach {
            Log.d(tag, it.toString())
        }.catch {
            Log.d(tag, it.toString())
        }.launchIn(viewModelScope)
    }

    private val tag = "CurrentWeatherViewModel"
}