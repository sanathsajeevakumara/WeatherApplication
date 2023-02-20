package com.sanathcoding.weatherapplication.data.remote.dto

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherDataDto: WeatherDataDto
)