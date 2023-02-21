package com.sanathcoding.weatherapplication.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sanathcoding.weatherapplication.presentation.components.WeatherCard
import com.sanathcoding.weatherapplication.presentation.components.WeatherForecast
import com.sanathcoding.weatherapplication.ui.theme.DarkBlue
import com.sanathcoding.weatherapplication.ui.theme.DeepBlue

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
        ) {
            WeatherCard(weatherState = viewModel.state, backgroundColor = DeepBlue)
            Spacer(modifier = Modifier.height(16.dp))
            WeatherForecast(weatherState = viewModel.state)
        }
        if (viewModel.state.isLoading) CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )

        viewModel.state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}