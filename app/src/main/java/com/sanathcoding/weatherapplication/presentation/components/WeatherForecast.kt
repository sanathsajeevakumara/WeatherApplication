package com.sanathcoding.weatherapplication.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanathcoding.weatherapplication.presentation.WeatherState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecast(
    weatherState: WeatherState,
    modifier: Modifier = Modifier
) {
    weatherState.weatherInfo?.weatherDataPerDay?.get(0)?.let { weatherData ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Today",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = modifier.height(16.dp))
            LazyRow(content = {
                items(weatherData.size) { i ->
                    HourlyWeatherDetail(
                        weatherData = weatherData[i],
                        modifier = Modifier
                            .height(140.dp)
                            .padding(horizontal = 1.dp)
                    )
                }
            })
        }
    }
}