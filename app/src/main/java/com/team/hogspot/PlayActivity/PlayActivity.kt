package com.team.hogspot.PlayActivity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.team.hogspot.R
import com.team.hogspot.UserActivity.UserActivity
import com.team.hogspot.composables.H1
import com.team.hogspot.composables.H2
import com.team.hogspot.composables.H3
import com.team.hogspot.composables.Header
import com.team.hogspot.composables.P
import com.team.hogspot.composables.PrimaryButton
import com.team.hogspot.composables.SecondaryButton
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

@Preview(showBackground = true)
@Composable
fun ResultPopupPreviewSuccess() {
    AppTheme {
        ResultPopup(4f)
    }
}

@Preview(showBackground = true)
@Composable
fun ResultPopupPreviewFail() {
    AppTheme {
        ResultPopup(100f)
    }
}

@Composable
fun PlayPage(currentLocation: LatLng?) {
    var geospot = LatLng(36.06879351237508, -94.17486855593883)
    var currentView by remember { mutableStateOf(View.MAP) }
    var endClickCount by remember { mutableIntStateOf(0) }
    var distance by remember { mutableFloatStateOf(0f) }

    fun calculateDistance(): Float {
        val results = FloatArray(1)
        if (currentLocation == null) {
            return -1f
        }
        Location.distanceBetween(
            currentLocation.latitude,
            currentLocation.longitude,
            geospot.latitude,
            geospot.longitude,
            results
        )
        Log.d("GoogleMapView", "Distance(m): ${results[0]}")
        return results[0]
    }

    fun onEnd() {
        if (endClickCount == 0) {
            distance = calculateDistance()
        }
        else if (endClickCount == 1) {
            // TODO: popup
        }
        endClickCount++
    }

    fun onSwitchView() {
        currentView = if (currentView == View.MAP) View.INFO else View.MAP
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colorScheme.background)
    ) {
        if (endClickCount == 2) {
            ResultPopup(distance)
        }
        Header(
            pageTitle = "Play",
            username = "Jordi",
            showBackButton = true,
            showUserProfile = false,
        )
        when (currentView) {
            View.MAP -> {
                if (currentLocation != null) {
                    GoogleMapView(currentLocation, modifier = Modifier.align(Alignment.Center), endClickCount)
                } else {
                    Text("Loading Map")
                }
            }

            View.INFO -> {
                InfoView(modifier = Modifier.align(Alignment.Center))
            }
        }
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .align(Alignment.Center)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            IconButton(onClick = { onSwitchView() }, modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(48.dp)) {
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
                onClick = { onEnd() },
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
    modifier: Modifier = Modifier,
    endClickCount: Int,
    geospot: LatLng = LatLng(36.06879351237508, -94.17486855593883)
) {
    val currentMarkerState = rememberMarkerState(position = currentLocation!!)
    val geoSpotMarkerState = rememberMarkerState(position = geospot)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 18f)
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
        if (endClickCount == 1) {
            Marker(
                state = geoSpotMarkerState,
                title = "Hogspot",
            )
            Circle(
                center = geospot,
                radius = 20.0,
                fillColor = Color(0x5500FF00),
                strokeColor = Color.Black,
                strokeWidth = 2f
            )
        }
    }
}

@Composable
fun InfoView(
    modifier: Modifier = Modifier
) {
    var showHint by remember { mutableStateOf(false) }

    fun onGetHint() {
        showHint = true
    }

    Column(
        modifier = modifier
    ) {
        H2(
            "HogSpot #123"
        )
        Image(
            painter = painterResource(id = R.drawable.spot_image1),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .clip(AppTheme.shape.container)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
        SecondaryButton(
            onClick = {onGetHint()},
            text = "get hint",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.3f)
                .align(Alignment.CenterHorizontally)
        )
        if (showHint) {
            P("Hint: There is no hint, if you do not know where we are based on this image you don't go to this school", modifier = Modifier.width(300.dp))
        }

    }
}

@Composable
fun ResultPopup(
    distance: Float
) {
    val localContext = LocalContext.current
    fun onFinish() {
        val intent = Intent(localContext, UserActivity::class.java)
        localContext.startActivity(intent)
    }
   Dialog({onFinish()}) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(AppTheme.shape.container)
                .background(AppTheme.colorScheme.backgroundSecondary)
                .padding(AppTheme.size.medium), verticalArrangement = Arrangement.spacedBy(AppTheme.size.normal)
        ) {
            Row {
                if (distance <= 20f) {
                    H2(
                        text = "Quest Complete"
                    )
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "HogSpot",
                        tint = AppTheme.colorScheme.difficultyEasy,
                        modifier = Modifier.size(40.dp).padding(start = 16.dp)
                    )
                } else {
                    H2(
                        text = "Quest Failed"
                    )
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "HogSpot",
                        tint = AppTheme.colorScheme.difficultyHard,
                        modifier = Modifier.size(40.dp).padding(start = 16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            H3(
                text = "Distance from spot: $distance meters",
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(
                onClick = { onFinish() },
                text = "Finish",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(0.75f)
            )
        }
   }

}

enum class View {
    MAP,
    INFO
}