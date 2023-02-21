package com.sanathcoding.weatherapplication.domain.repository

import com.sanathcoding.weatherapplication.domain.util.Resource
import com.sanathcoding.weatherapplication.domain.weather_type.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lng: Double): Resource<WeatherInfo>
}