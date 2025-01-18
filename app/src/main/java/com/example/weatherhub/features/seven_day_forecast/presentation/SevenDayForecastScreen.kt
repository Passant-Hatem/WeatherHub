package com.example.weatherhub.features.seven_day_forecast.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherhub.R
import com.example.weatherhub.core.mics.Action
import com.example.weatherhub.core.presentaion.components.LoadingScreen
import com.example.weatherhub.core.presentaion.components.SimpleAppbar
import com.example.weatherhub.core.presentaion.theme.WeatherHubScaffold
import com.example.weatherhub.features.seven_day_forecast.presentation.components.WeatherForecastList
import com.example.weatherhub.features.seven_day_forecast.presentation.model.SevenDayForecastEffect
import com.example.weatherhub.features.seven_day_forecast.presentation.model.SevenDayForecastIntent
import com.example.weatherhub.features.seven_day_forecast.presentation.model.SevenDayForecastState
import com.example.weatherhub.features.today_weather.presentation.model.toForecastUIModel

@Composable
fun SevenDayForecastScreen(
    city: String,
    onNavigateBack: Action
) {
    val context = LocalContext.current
    val viewModel: SevenDayForecastViewModel = hiltViewModel()

    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.effect) {
        when (val effect = uiState.effect) {
            is SevenDayForecastEffect.ShowError -> {
                val error = context.resources.getString(effect.errorMessage)
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                viewModel.consumeEffect()
            }

            else -> Unit
        }
    }

    LaunchedEffect(city) {
        viewModel.sendIntent(SevenDayForecastIntent.LoadForecast(city))
    }

    WeatherHubScaffold(
        appBar = {
            SimpleAppbar(
                title = when (val state = uiState.state) {
                    is SevenDayForecastState.Content -> stringResource(
                        R.string.seven_day_forecast_screen_title,
                        state.city
                    )

                    else -> stringResource(R.string.seven_day_forecast_screen_title_placeholder)
                },
                onBackPressed = onNavigateBack
            )
        }
    ) {
        when (uiState.state) {
            is SevenDayForecastState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            is SevenDayForecastState.Content -> WeatherForecastList(
                forecast = (uiState.state as SevenDayForecastState.Content).forecast.map { it.toForecastUIModel() }
            )
            // todo: replace with screen
            is SevenDayForecastState.Empty -> Text(
                text = stringResource(R.string.no_data_available),
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
    }
}
