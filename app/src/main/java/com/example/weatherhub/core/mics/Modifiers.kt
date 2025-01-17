package com.example.weatherhub.core.mics

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("SuspiciousModifierThen")
fun Modifier.screenPadding(
    start: Dp = 16.dp,
    top: Dp = 0.dp,
    end: Dp = 16.dp,
    bottom: Dp = 16.dp
) = this.then(padding(start = start, top = top, end = end, bottom = bottom))