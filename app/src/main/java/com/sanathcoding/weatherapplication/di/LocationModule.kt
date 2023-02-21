package com.sanathcoding.weatherapplication.di

import com.sanathcoding.weatherapplication.data.remote.location.CurrentLocationTracker
import com.sanathcoding.weatherapplication.domain.location.LocationTracker
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
    abstract fun bindLocationTracker(currentLocationTracker: CurrentLocationTracker): LocationTracker

}