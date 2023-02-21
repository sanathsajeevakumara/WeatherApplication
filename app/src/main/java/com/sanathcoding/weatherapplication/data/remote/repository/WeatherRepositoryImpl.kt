package com.sanathcoding.weatherapplication.data.remote.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.sanathcoding.weatherapplication.data.remote.WeatherApi
import com.sanathcoding.weatherapplication.data.remote.mapper.toWeatherInfo
import com.sanathcoding.weatherapplication.domain.repository.WeatherRepository
import com.sanathcoding.weatherapplication.domain.util.Resource
import com.sanathcoding.weatherapplication.domain.weather_type.WeatherInfo
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, lng: Double): Resource<WeatherInfo> {
        return try {
            val response = api.getWeatherData(lat, lng).toWeatherInfo()
            Resource.Success(
                data = response
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage?: "An Unknown error occurred", data = null)
        }catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage?: "Please check yo internet connection", data = null)
        }
    }
}