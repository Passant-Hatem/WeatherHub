package com.example.weatherhub.core.presentaion.model

import com.example.weatherhub.R
import retrofit2.HttpException
import java.net.UnknownHostException

fun getLocalizedErrorMessage(exception: Throwable): Int {
    return when (exception) {
        is UnknownHostException -> R.string.connection_error_message
        is HttpException -> {
            if (exception.code() == 404) R.string.city_not_found_error_message
            else R.string.server_error_message
        }
        else -> R.string.general_error_message
    }
}