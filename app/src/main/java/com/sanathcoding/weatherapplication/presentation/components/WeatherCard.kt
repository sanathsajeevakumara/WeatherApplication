package com.sanathcoding.weatherapplication.presentation.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanathcoding.weatherapplication.R
import com.sanathcoding.weatherapplication.presentation.WeatherState
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCard(
    weatherState: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    weatherState.weatherInfo?.currentWeatherData?.let { data ->

        Card(
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today ${
                        data.time.format(DateTimeFormatter.ofPattern("HH:mm "))
                    }",
                    modifier = Modifier.align(Alignment.End),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = "Weather Icons",
                    modifier = modifier.width(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${data.temperatureCelsius}°C",
                    fontSize = 50.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${data.weatherType.weatherDesc}°C",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDetail(
                        value = data.pressure.toInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDetail(
                        value = data.humidity,
                        unit = "%",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                        textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDetail(
                        value = data.windSpeed.toInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                        textStyle = TextStyle(color = Color.White)
                    )
                }
            }
        }
    }
}