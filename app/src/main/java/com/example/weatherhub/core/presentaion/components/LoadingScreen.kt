package com.example.weatherhub.core.presentaion.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingScreen(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}