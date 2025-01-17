package com.example.weatherhub.core.presentaion.model

import androidx.annotation.StringRes

sealed class ActionStates {
    data object Loading : ActionStates()
    data object Success : ActionStates()

    data class Error(@StringRes val errorMessage: Int) : ActionStates()
}