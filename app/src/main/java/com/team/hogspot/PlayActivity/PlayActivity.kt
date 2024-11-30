package com.team.hogspot.PlayActivity

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.PrimaryButton
import com.team.hogspot.ui.theme.AppTheme
import com.team.hogspot.util.LocationUtilCallback
import com.team.hogspot.util.createLocationCallback
import com.team.hogspot.util.createLocationRequest

class PlayActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var mLocationCallback: LocationCallback

    //Member object for the last known location
    private lateinit var mCurrentLocation: Location
    private var currentLocationState = mutableStateOf<LatLng?>(null)
    private var locationPermissionEnabled = false
    private var locationRequestsEnabled = false
    private val LOCATION_PERMISSION_REQUEST_CODE = 1000
    private val LOG_TAG = "PlayActivity"
    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            //If successful, startLocationRequests
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                locationPermissionEnabled = true
                startLocationRequests()
            }
            //If successful at coarse detail, we still want those
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                locationPermissionEnabled = true
                startLocationRequests()
            }

            else -> {
                //Otherwise, send toast saying location is not enabled
                locationPermissionEnabled = false
                Toast.makeText(this, "Location Not Enabled", Toast.LENGTH_LONG)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                PlayPage(currentLocation = currentLocationState.value)
            }
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        checkForLocationPermission()
    }


    private fun checkForLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                startLocationRequests()
            }

            else -> {
                requestPermissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
            }
        }
    }

    //LocationUtilCallback object
    //Dynamically defining two results from locationUtils
    //Namely requestPermissions and locationUpdated
    private val locationUtilCallback = object : LocationUtilCallback {
        //If locationUtil request fails because of permission issues
        //Ask for permissions
        override fun requestPermissionCallback() {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }

        //If locationUtil returns a Location object
        //Populate the current location and log
        override fun locationUpdatedCallback(location: Location) {
            mCurrentLocation = location
//            mapsFragment.changeCenterLocation(GeoPoint(location.latitude, location.longitude))
            currentLocationState.value = LatLng(location.latitude, location.longitude)
            Log.d(
                LOG_TAG,
                "Location is [Lat: ${location.latitude}, Long: ${location.longitude}]"
            )
        }
    }

    private fun startLocationRequests() {
        //If we aren't currently getting location updates
        if (!locationRequestsEnabled) {
            //create a location callback
            mLocationCallback = createLocationCallback(locationUtilCallback)
            //and request location updates, setting the boolean equal to whether this was successful
            locationRequestsEnabled =
                createLocationRequest(this, fusedLocationClient, mLocationCallback)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayPagePreview() {
    AppTheme {
        PlayPage(LatLng(36.06879351237508, -94.17486855593883))
    }
}

@Composable
fun PlayPage(currentLocation: LatLng?, onEnd: () -> Unit = {}) {
    var currentView by remember { mutableStateOf(View.MAP) }
    var endClickCount by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colorScheme.background)
    ) {
        Header(
            pageTitle = "Play",
            username = "Jordi",
            showBackButton = true,
            showUserProfile = false,
        )
        when (currentView) {
            View.MAP -> {
                if (currentLocation != null) {
                    GoogleMapView(currentLocation, modifier = Modifier.align(Alignment.Center))
                } else {
                    Text("Loading Map")
                }
            }

            View.INFO -> {
                Text("Info")
            }
        }
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .align(Alignment.Center)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            IconButton(onClick = {}, modifier = Modifier.align(Alignment.CenterVertically).size(48.dp)) {
                when (currentView) {
                    View.MAP -> {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Info",
                            tint = AppTheme.colorScheme.primary,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    View.INFO -> {
                        Icon(
                            imageVector = Icons.Filled.Place,
                            contentDescription = "Map",
                            tint = AppTheme.colorScheme.primary,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
            PrimaryButton(
                onClick = onEnd,
                text = "End",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.75f)
            )
        }
    }
}

@Composable
fun GoogleMapView(
    currentLocation: LatLng?,
    modifier: Modifier = Modifier
) {
    val uark = LatLng(36.06879351237508, -94.17486855593883)
    val currentMarkerState = rememberMarkerState(position = currentLocation!!)
    val geoSpotMarkerState = rememberMarkerState(position = uark)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 15f)
    }

    GoogleMap(
        modifier = modifier
            .fillMaxWidth()
            .height(700.dp)
            .background(color = androidx.compose.ui.graphics.Color.Gray),
        cameraPositionState = cameraPositionState,
    ) {
        currentLocation.let {
            Marker(
                state = rememberMarkerState(position = it),
                title = "Current Location",
            )
        }
    }
}

enum class View {
    MAP,
    INFO
}