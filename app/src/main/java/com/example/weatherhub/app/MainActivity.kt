package com.example.weatherhub.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.weatherhub.core.presentaion.theme.WeatherHubTheme
import com.example.weatherhub.features.current_weather.presentation.CurrentWeatherScreen
import com.example.weatherhub.features.current_weather.presentation.CurrentWeatherScreenRoute
import com.example.weatherhub.features.seven_day_forecast.presentation.SevenDayForecastScreen
import com.example.weatherhub.features.seven_day_forecast.presentation.SevenDayForecastScreenRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherHubTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = CurrentWeatherScreenRoute
                ) {
                    composable<CurrentWeatherScreenRoute> {
                        CurrentWeatherScreen {
                            navController.navigate(SevenDayForecastScreenRoute(it))
                        }
                    }
                    composable<SevenDayForecastScreenRoute> {
                        val args = it.toRoute<SevenDayForecastScreenRoute>()
                        SevenDayForecastScreen(args.city)
                    }
                }
            }
        }
    }
}