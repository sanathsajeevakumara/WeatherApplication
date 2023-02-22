package com.sanathcoding.weatherapplication.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
            Card(
                colors = CardDefaults.cardColors(containerColor = DeepBlue),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.padding(16.dp)
                    .align(Alignment.Center)
            ) {
                Text(
                    text = error,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}