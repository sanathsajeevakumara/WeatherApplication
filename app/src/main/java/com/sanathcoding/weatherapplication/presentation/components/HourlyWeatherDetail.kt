package com.sanathcoding.weatherapplication.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sanathcoding.weatherapplication.domain.weather_type.WeatherData
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherDetail(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    textColor: Color =  Color.White
) {

    val formattedTime = remember {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm ")
        )
    }

    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formattedTime,
            color = Color.LightGray
        )
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = "Weather Icons",
            modifier = Modifier.width(40.dp)
        )
        Text(
            text = "${weatherData.temperatureCelsius}°C",
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}