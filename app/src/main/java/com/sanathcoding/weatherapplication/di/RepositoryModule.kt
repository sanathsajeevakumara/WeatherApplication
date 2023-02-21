package com.sanathcoding.weatherapplication.di

import com.sanathcoding.weatherapplication.data.remote.repository.WeatherRepositoryImpl
import com.sanathcoding.weatherapplication.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository

}