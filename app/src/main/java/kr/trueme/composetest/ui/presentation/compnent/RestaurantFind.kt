package kr.trueme.composetest.ui.presentation.compnent

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import kr.trueme.composetest.ui.data.datasource.network.searchMap
import kr.trueme.composetest.ui.data.model.Document

@Composable
fun RestaurantFind(
    foodName: String,
    navController: NavController
) {
    val context = LocalContext.current
    val activity = (context as? Activity)

    var check by remember {
        mutableStateOf(false)
    }
    val list = rememberSaveable { mutableStateOf(listOf<Document>()) }

    LaunchedEffect(Unit) {
        val locationPermission = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            }
        } else {
            LocationServices.getFusedLocationProviderClient(context).lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    searchMap(
                        query = foodName,
                        x = location.longitude.toDouble(),
                        y = location.latitude.toDouble(),
                        radius = 1000,
                        documents = list,
                    )
                }
            }
        }
    }

    LaunchedEffect(list.value) {
        if (list.value.isNotEmpty()) {
            val document = list.value.random()
            navController.navigate("restaurant?url=${document.placeUrl}")
        } else {

        }
    }


}