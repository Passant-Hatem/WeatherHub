package com.example.weatherhub.features.today_weather.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherhub.R
import com.example.weatherhub.core.mics.Consumer
import com.example.weatherhub.core.mics.screenPadding
import com.example.weatherhub.core.presentaion.components.LoadingScreen
import com.example.weatherhub.core.presentaion.model.ActionStates
import com.example.weatherhub.core.presentaion.theme.WeatherHubScaffold
import com.example.weatherhub.features.today_weather.presentation.components.OnboardingMessage
import com.example.weatherhub.core.presentaion.components.SearchField
import com.example.weatherhub.features.today_weather.presentation.components.TodayWeatherData
import com.example.weatherhub.features.today_weather.presentation.model.toUIModel

@Composable
fun TodayWeatherScreen(
    onSeeSevenDayForecast: Consumer<String>
) {
    val context = LocalContext.current
    val viewModel: TodayWeatherViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val actionState = state.action

    if (actionState is ActionStates.Error) {
        val error = stringResource(id = actionState.errorMessage)
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        viewModel.resetErrorAction()
    }

    // todo: In case of an error, a "Retry" button can be displayed.

    WeatherHubScaffold {
        Column(
            modifier = Modifier
                .screenPadding()
                .fillMaxSize()
        ) {
            SearchField(
                searchQuery = state.searchQuery ?: "",
                onQueryChanged = viewModel::onUpdateSearchQuery,
                shouldHideKeyboard = actionState == ActionStates.Success,
                onSearchClicked = viewModel::onSearchClicked
            )
            TodayWeatherScreenContent(
                state = state,
                onSeeSevenDayForecast = onSeeSevenDayForecast
            )
        }
    }
}

@Composable
private fun TodayWeatherScreenContent(
    state: CurrentWeatherUIState,
    onSeeSevenDayForecast: (String) -> Unit
) {
    when {
        state.action == ActionStates.Loading -> LoadingScreen(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
        )

        state.weather != null -> TodayWeatherData(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f), weather = state.weather.toUIModel()
        )

        state.isOnboarding -> OnboardingMessage()
    }

    if (!state.isOnboarding && state.cityName != null) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = { onSeeSevenDayForecast(state.cityName) }) {
                Text(text = stringResource(R.string.see_seven_day_forecast))
            }
        }
    }
}

