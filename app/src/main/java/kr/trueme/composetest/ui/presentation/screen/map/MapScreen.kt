package kr.trueme.composetest.ui.presentation.screen.map

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.location.Location
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.naver.maps.map.LocationSource
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.data.datasource.network.searchMap
import kr.trueme.composetest.ui.data.model.Document
import kr.trueme.composetest.ui.presentation.compnent.ChipStyle
import kr.trueme.composetest.ui.presentation.compnent.StandardTabRow
import kr.trueme.composetest.ui.presentation.screen.map.components.FoodGoodPlaceItem
import kr.trueme.composetest.ui.presentation.screen.map.components.RadiusSettingChip
import kr.trueme.composetest.ui.presentation.screen.map.tab.MapViewTab
import kr.trueme.composetest.ui.theme.Gray400
import kr.trueme.composetest.ui.theme.Orange400
import kr.trueme.composetest.ui.theme.PretendardFamily
import kotlin.math.roundToInt

@Composable
fun MapScreen(navController: NavController) {

    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardTabRow(
            modifier = Modifier,
            titles = listOf("목록으로 보기", "지도로 보기")
        ) { index ->
            when (index) {
                0 -> {
                    MapListLayout(navController)
                }
                1 -> {
                    MapViewTab(navController)
                }
            }
        }
    }
}

val chipStyle: ChipStyle = ChipStyle(
    selectedColor = Orange400,
    unselectedColor = Color.White,
    chipTextStyle = TextStyle(
        fontFamily = PretendardFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 14.sp,
    ),
    selectedTextColor = Color.White,
    unselectedTextColor = Gray400,
    chipModifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
)

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapListLayout(navController: NavController) {


    val context = LocalContext.current
    val activity = (context as Activity)

    val permissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    )

    LaunchedEffect(Unit) {
        permissionsState.launchMultiplePermissionRequest()
    }

    Spacer(modifier = Modifier.height(8.dp))

//    Button(onClick = {
////        RequestPermissionsUtil(context).requestLocation()
//        permissionsState.launchMultiplePermissionRequest()
//    })

    val scope = rememberCoroutineScope()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clickable {

                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location_outline),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "내 위치")
                Spacer(modifier = Modifier.width(4.dp))
                Icon(painter = painterResource(id = R.drawable.ic_down), contentDescription = null)
            }
            RadiusSettingChip(
                modifier = Modifier.align(Alignment.Bottom),
                onClick = {

                }
            )
        }

        Text(text = "우리 동네 맛집",
            style = TextStyle(
                fontFamily = PretendardFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.sp
            ))
        Spacer(modifier = Modifier.height(8.dp))
        if (permissionsState.allPermissionsGranted) {
            GoodPlaceSection(navController = navController, permissionsState.allPermissionsGranted)
        }

    }

}


@Composable
fun GoodPlaceSection(
    navController: NavController,
    hasPermission: Boolean,
) {
    val context = LocalContext.current
    val goodPlaces = rememberSaveable { mutableStateOf(listOf<Document>()) }
    LaunchedEffect(hasPermission) {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { success: Location? ->
                success?.let { location ->
                    searchMap(
                        query = "맛집",
                        x = location.longitude.toDouble(),
                        y = location.latitude.toDouble(),
                        radius = 20000,
                        documents = goodPlaces,
                    )
                }
            }
            .addOnFailureListener { fail -> }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(goodPlaces.value) {
            FoodGoodPlaceItem(
                modifier = Modifier.fillMaxWidth(),
                name = it.placeName,
                categories = it.categoryName?.split(" > ")?.drop(1) ?: listOf(),
                address = it.roadAddressName,
                distance = if (it.distance == "") 0 else it.distance.toInt(),
                onClick = {
                    navController.navigate("restaurant?url=${it.placeUrl}")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}

@Composable
fun RadiusSlider() {
    var sliderPosition by remember { mutableFloatStateOf(500f) }
    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { value ->
                sliderPosition = (value / 100).roundToInt() * 100f
            },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colors.secondary,
                activeTrackColor = MaterialTheme.colors.secondary,
                inactiveTrackColor = MaterialTheme.colors.secondaryVariant,
            ),
            steps = 3,
            valueRange = 500f..1000f
        )
        Text(text = sliderPosition.toString())
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun rememberLocationSource(): LocationSource {
    val permissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    )
    val context = LocalContext.current
    val locationSource = remember {
        object : FusedLocationSource(context) {
            override fun hasPermissions(): Boolean {
                return permissionsState.allPermissionsGranted
            }

            override fun onPermissionRequest() {
                permissionsState.launchMultiplePermissionRequest()
            }
        }
     }


    val allGranted = permissionsState.allPermissionsGranted
    LaunchedEffect(allGranted) {
        if (allGranted) {
            locationSource.onPermissionGranted()
        }
    }
    return locationSource
}

private abstract class FusedLocationSource(context: Context) : LocationSource {

    private val callback = object : FusedLocationCallback(context.applicationContext) {
        override fun onLocationChanged(location: Location?) {
            lastLocation = location
        }
    }

    private var listener: LocationSource.OnLocationChangedListener? = null
    private var isListening: Boolean = false
    private var lastLocation: Location? = null
        set(value) {
            field = value
            if (listener != null && value != null) {
                listener?.onLocationChanged(value)
            }
        }

    abstract fun hasPermissions(): Boolean
    abstract fun onPermissionRequest()

    fun onPermissionGranted() {
        setListening(true)
    }

    override fun activate(listener: LocationSource.OnLocationChangedListener) {
        this.listener = listener
        if (isListening.not()) {
            if (hasPermissions()) {
                setListening(true)
            } else {
                onPermissionRequest()
            }
        }
    }

    override fun deactivate() {
        if (isListening) {
            setListening(false)
        }
        this.listener = null
    }

    private fun setListening(listening: Boolean) {
        if (listening) {
            callback.startListening()
        } else {
            callback.stopListening()
        }
        isListening = listening
    }

    private abstract class FusedLocationCallback(private val context: Context) {

        private val locationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                onLocationChanged(locationResult.lastLocation)
            }
        }

        fun startListening() {
            GoogleApiClient.Builder(context)
                .addConnectionCallbacks(object : GoogleApiClient.ConnectionCallbacks {
                    @SuppressLint("MissingPermission")
                    override fun onConnected(bundle: Bundle?) {
                        val request = LocationRequest()
                        request.priority = 100
                        request.interval = 1000L
                        request.fastestInterval = 1000L
                        LocationServices.getFusedLocationProviderClient(context)
                            .requestLocationUpdates(request, locationCallback, null)
                    }

                    override fun onConnectionSuspended(i: Int) {}
                })
                .addApi(LocationServices.API)
                .build()
                .connect()
        }

        fun stopListening() {
            LocationServices
                .getFusedLocationProviderClient(context)
                .removeLocationUpdates(locationCallback)
        }

        abstract fun onLocationChanged(location: Location?)
    }
}

@Composable
fun MyApp(fusedLocationClient: FusedLocationProviderClient) {
    var location by remember { mutableStateOf<String?>(null) }
    val locationPermissionRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
            getLocation(fusedLocationClient) { lat, lon ->
                location = "Lat: $lat, Lon: $lon"
            }
        } else {
            location = "Permission Denied"
        }
    }

    LaunchedEffect(true) {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = location ?: "Fetching location...")
            }
        }
    )
}

@SuppressLint("MissingPermission")
fun getLocation(
    fusedLocationClient: FusedLocationProviderClient,
    onLocationRetrieved: (Double, Double) -> Unit
) {
    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        location?.let {
            onLocationRetrieved(it.latitude, it.longitude)
        } ?: run {
            onLocationRetrieved(0.0, 0.0) // 기본값 (위치가 없는 경우)
        }
    }
}
