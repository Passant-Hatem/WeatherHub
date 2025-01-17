package com.example.weatherhub.features.today_weather.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherhub.R
import com.example.weatherhub.core.presentaion.model.ActionStates
import com.example.weatherhub.core.presentaion.model.getLocalizedErrorMessage
import com.example.weatherhub.features.today_weather.domain.GetCityWeather
import com.example.weatherhub.features.today_weather.domain.GetLastSearchedCity
import com.example.weatherhub.features.today_weather.domain.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TodayWeatherViewModel @Inject constructor(
    private val getCityWeather: GetCityWeather,
    private val getLastSearchedCity: GetLastSearchedCity
) : ViewModel() {
    private val _state = MutableStateFlow(CurrentWeatherUIState())
    val uiState: StateFlow<CurrentWeatherUIState> = _state.asStateFlow()

    init {
        getLatestCityName()
    }

    private fun getLatestCityName() {
        getLastSearchedCity().onEach { cityName ->
            if (!cityName.isNullOrEmpty()) {
                _state.value = _state.value.copy(isOnboarding = false)
                getWeather(cityName)
            }
        }.catch {
            Log.e(TAG, it.toString())
        }.launchIn(viewModelScope)
    }

    fun onUpdateSearchQuery(searchQuery: String) {
        _state.value = _state.value.copy(searchQuery = searchQuery)
    }

    fun onSearchClicked() {
        val query = _state.value.searchQuery
        if (query.isNullOrEmpty()) {
            _state.value =
                _state.value.copy(action = ActionStates.Error(R.string.please_enter_a_value))
            return
        }
        // todo: do we need more constrains? like removing all whitespaces, or warn user about specific characters
        getWeather(query.trim())
    }

    private fun getWeather(cityName: String) {

        _state.value = _state.value.copy(action = ActionStates.Loading)

        getCityWeather(cityName).onEach { weather ->
            _state.value = _state.value.copy(
                weather = weather,
                action = ActionStates.Success,
                cityName = weather.cityName,
                isOnboarding = false
            )
        }.catch { throwable -> onFailure(throwable) }.launchIn(viewModelScope)
    }

    private fun onFailure(throwable: Throwable) {
        val errorMessageResId = getLocalizedErrorMessage(throwable)
        _state.value = _state.value.copy(
            action = ActionStates.Error(errorMessageResId)
        )
        Log.e(TAG, "Error fetching weather data", throwable)
    }

    fun resetErrorAction() {
        _state.value = _state.value.copy(action = null)
    }

    private val TAG = "CurrentWeatherViewModel"
}

data class CurrentWeatherUIState(
    val searchQuery: String? = null,
    val cityName: String? = null,
    val weather: Weather? = null,
    val action: ActionStates? = null,
    val isOnboarding: Boolean = true // Add explicit onboarding state
)