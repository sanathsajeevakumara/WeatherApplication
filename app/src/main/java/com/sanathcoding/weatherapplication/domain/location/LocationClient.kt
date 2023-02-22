package com.sanathcoding.weatherapplication.domain.location

import android.location.Location

interface LocationClient {
    suspend fun getCurrentLocation(): Location?
}