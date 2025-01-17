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
import com.example.weatherhub.features.today_weather.presentation.TodayWeatherScreen
import com.example.weatherhub.features.today_weather.presentation.model.TodayWeatherScreenRoute
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
                    startDestination = TodayWeatherScreenRoute
                ) {
                    composable<TodayWeatherScreenRoute> {
                        TodayWeatherScreen {
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