package com.sanathcoding.weatherapplication.data.remote.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.sanathcoding.weatherapplication.domain.location.LocationClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class CurrentLocationClient @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationClient {
    override suspend fun getCurrentLocation(): Location? {
        val hasCoarseLocationPermissionAccess = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasFineLocationPermissionAccess = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE)
                as LocationManager
        val isGpsEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        //Don't have location access
        if (!isGpsEnable || !hasCoarseLocationPermissionAccess || !hasFineLocationPermissionAccess)
            return null

        //Have location access
        return suspendCancellableCoroutine { continuation ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) continuation.resume(result)
                    else continuation.resume(null)
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener { continuation.resume(it) }
                addOnFailureListener { continuation.resume(null) }
                addOnCanceledListener { continuation.cancel() }
            }
        }

    }
}