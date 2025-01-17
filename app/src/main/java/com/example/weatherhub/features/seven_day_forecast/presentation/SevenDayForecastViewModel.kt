package com.example.weatherhub.features.seven_day_forecast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherhub.core.presentaion.model.getLocalizedErrorMessage
import com.example.weatherhub.features.seven_day_forecast.domain.GetSevenDayForecast
import com.example.weatherhub.features.seven_day_forecast.presentation.model.SevenDayForecastEffect
import com.example.weatherhub.features.seven_day_forecast.presentation.model.SevenDayForecastIntent
import com.example.weatherhub.features.seven_day_forecast.presentation.model.SevenDayForecastState
import com.example.weatherhub.features.seven_day_forecast.presentation.model.SevenDayForecastViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SevenDayForecastViewModel @Inject constructor(
    private val getSevenDayForecast: GetSevenDayForecast
) : ViewModel() {

    private val _state = MutableStateFlow(SevenDayForecastViewState())
    val state: StateFlow<SevenDayForecastViewState> = _state.asStateFlow()

    fun sendIntent(intent: SevenDayForecastIntent) {
        when (intent) {
            is SevenDayForecastIntent.LoadForecast -> loadForecast(intent.city)
        }
    }

    private fun loadForecast(city: String) {
        _state.update { it.copy(state = SevenDayForecastState.Loading) }
        getSevenDayForecast(city)
            .onEach { forecast ->
                if (forecast.weather.isEmpty()) {
                    _state.update { it.copy(state = SevenDayForecastState.Empty) }
                } else {
                    _state.update {
                        it.copy(
                            state = SevenDayForecastState.Content(
                                city = forecast.cityName,
                                forecast = forecast.weather
                            )
                        )
                    }
                }
            }
            .catch { throwable ->
                val errorMessageResId = getLocalizedErrorMessage(throwable)
                _state.update {
                    it.copy(
                        effect = SevenDayForecastEffect.ShowError(errorMessageResId)
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun consumeEffect() {
        _state.update { it.copy(effect = null) }
    }
}
