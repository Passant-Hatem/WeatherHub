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
import com.example.weatherhub.features.current_weather.CurrentWeatherScreen
import com.example.weatherhub.features.current_weather.CurrentWeatherScreenRoute
import com.example.weatherhub.features.find_city.FindCityScreen
import com.example.weatherhub.features.find_city.FindCityScreenRoute
import com.example.weatherhub.features.seven_day_forecast.SevenDayForecastScreen
import com.example.weatherhub.features.seven_day_forecast.SevenDayForecastScreenRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherHubTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = FindCityScreenRoute
                ) {
                    composable<FindCityScreenRoute> {
                        FindCityScreen(
                            onNavigateToSevenDayForecast = {
                                navController.navigate(SevenDayForecastScreenRoute(it))
                            },
                            onNavigateToCurrentWeather = {
                                navController.navigate(
                                    CurrentWeatherScreenRoute
                                )
                            })
                    }
                    composable<CurrentWeatherScreenRoute> {
                        CurrentWeatherScreen()
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