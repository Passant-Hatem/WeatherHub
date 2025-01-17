package com.example.weatherhub.core.presentaion.model

import android.os.Build
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

//fixme: move to an external library

fun kelvinToCelsius(kelvin: Double): String {
    val temperatureInCelsius = kelvin - 273.15
    return " ${"%.2f".format(temperatureInCelsius)}°C"
}

fun convertToLocalTime(timestamp: Long, timezoneOffset: Int): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Use java.time for API 26+
        Instant.ofEpochSecond(timestamp)
            .atOffset(ZoneOffset.ofTotalSeconds(timezoneOffset))
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    } else {
        // Use legacy Date and SimpleDateFormat for older APIs
        val date = Date((timestamp + timezoneOffset) * 1000)
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        formatter.format(date)
    }
}

fun getIconURL(iconCode: String) =
    "https://openweathermap.org/img/wn/${iconCode}@2x.png"