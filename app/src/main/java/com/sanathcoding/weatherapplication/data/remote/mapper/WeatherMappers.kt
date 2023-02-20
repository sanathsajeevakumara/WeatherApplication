package com.sanathcoding.weatherapplication.data.remote.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.sanathcoding.weatherapplication.data.remote.dto.WeatherDataDto
import com.sanathcoding.weatherapplication.data.remote.dto.WeatherDto
import com.sanathcoding.weatherapplication.domain.weather_type.WeatherData
import com.sanathcoding.weatherapplication.domain.weather_type.WeatherInfo
import com.sanathcoding.weatherapplication.domain.weather_type.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperature[index]
        val pressure = pressureMsl[index]
        val windSpeed = windSpeeds[index]
        val weatherCode = weatherCodes[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map {
            it.data
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherDataDto.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30 ) now.hour else (now.hour + 1)
            it.time.hour == hour
    }
    return  WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}

