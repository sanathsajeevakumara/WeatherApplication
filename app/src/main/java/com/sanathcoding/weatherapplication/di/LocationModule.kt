package com.sanathcoding.weatherapplication.di

import com.sanathcoding.weatherapplication.data.remote.location.CurrentLocationClient
import com.sanathcoding.weatherapplication.domain.location.LocationClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(currentLocationTracker: CurrentLocationClient): LocationClient

}