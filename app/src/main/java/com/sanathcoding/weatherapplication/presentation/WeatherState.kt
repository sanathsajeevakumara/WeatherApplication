package com.sanathcoding.weatherapplication.presentation

import com.sanathcoding.weatherapplication.domain.weather_type.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)