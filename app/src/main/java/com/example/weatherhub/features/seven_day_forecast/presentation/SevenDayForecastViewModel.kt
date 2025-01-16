package com.example.weatherhub.features.seven_day_forecast.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherhub.features.seven_day_forecast.domain.GetSevenDayForecast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SevenDayForecastViewModel @Inject constructor(
    private val getSevenDayForecast: GetSevenDayForecast
) : ViewModel() {
    fun getWeather(city:String) {
        getSevenDayForecast(city).onEach {
            Log.d(tag, it.toString())
        }.catch {
            Log.d(tag, it.toString())
        }.launchIn(viewModelScope)
    }

    private val tag = "SevenDayForecastViewModel"
}