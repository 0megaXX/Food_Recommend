package kr.trueme.composetest.ui.presentation.screen.map.tab

import android.location.Location
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.OverlayImage
import kotlinx.coroutines.launch
import kr.trueme.composetest.R
import kr.trueme.composetest.ui.data.datasource.network.searchMap
import kr.trueme.composetest.ui.data.model.Document
import kr.trueme.composetest.ui.presentation.compnent.BottomSheetDragHandle
import kr.trueme.composetest.ui.presentation.compnent.CustomAlertDialog
import kr.trueme.composetest.ui.presentation.compnent.CustomAlertDialogState
import kr.trueme.composetest.ui.presentation.compnent.IconChipGroup
import kr.trueme.composetest.ui.presentation.compnent.IconChipState
import kr.trueme.composetest.ui.presentation.screen.map.ChinaMarkerUiModel
import kr.trueme.composetest.ui.presentation.screen.map.DesertMarkerUiModel
import kr.trueme.composetest.ui.presentation.screen.map.FastMarkerUiModel
import kr.trueme.composetest.ui.presentation.screen.map.GoodPlaceSection
import kr.trueme.composetest.ui.presentation.screen.map.JapanMarkerUiModel
import kr.trueme.composetest.ui.presentation.screen.map.KoreaMarkerUiModel
import kr.trueme.composetest.ui.presentation.screen.map.RadiusSlider
import kr.trueme.composetest.ui.presentation.screen.map.RestaurantMapViewModel
import kr.trueme.composetest.ui.presentation.screen.map.RestaurantMarkerUiModel
import kr.trueme.composetest.ui.presentation.screen.map.WesternMarkerUiModel
import kr.trueme.composetest.ui.presentation.screen.map.chipStyle
import kr.trueme.composetest.ui.presentation.screen.map.components.RadiusSettingChip
import kr.trueme.composetest.ui.presentation.screen.map.rememberLocationSource
import kr.trueme.composetest.ui.theme.PretendardFamily
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MapViewTab(
    navController: NavController,
    viewModel: RestaurantMapViewModel = viewModel()
) {
    val elements by remember {
        mutableStateOf(
            listOf(
                IconChipState("한식", "한식", R.drawable.foodcategory_1, R.drawable.food_category_1_gray, mutableStateOf(true)),
                IconChipState("중식", "중식", R.drawable.foodcategory_2, R.drawable.food_category_2_gray, mutableStateOf(false)),
                IconChipState("일식", "일식", R.drawable.foodcategory_3, R.drawable.food_category_3_gray, mutableStateOf(false)),
                IconChipState("양식", "양식", R.drawable.foodcategory_4, R.drawable.food_category_4_gray, mutableStateOf(false)),
                IconChipState("아시아", "아시아음식", R.drawable.foodcategory_6, R.drawable.food_category_6_gray, mutableStateOf(false)),
                IconChipState("패스트 푸드", "패스트 푸드", R.drawable.foodcategory_5, R.drawable.food_category_5_gray, mutableStateOf(false)),
                IconChipState("디저트", "카페", R.drawable.foodcategory_7, R.drawable.food_category_7_gray, mutableStateOf(false)),
            )
        )
    }

    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            Column(modifier = Modifier.padding(16.dp)) {
                RadiusSlider()
            }

        }
    ) {
        BottomSheetScaffold(
            sheetPeekHeight = 190.dp,
            sheetContent = {
                Column(Modifier.fillMaxSize()) {
                    BottomSheetDragHandle(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Row {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = "우리 동네 맛집",
                                style = TextStyle(
                                    fontFamily = PretendardFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    lineHeight = 20.sp,
                                    letterSpacing = 0.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            RadiusSettingChip(
                                modifier = Modifier.align(Alignment.Bottom),
                                onClick = {
                                    scope.launch { state.show() }
                                }
                            )
                        }
                        GoodPlaceSection(navController, true)
                    }

                }
            })
        {

            val list = rememberSaveable { mutableStateOf(listOf<Document>()) }

            val context = LocalContext.current
            var selectCategory by remember { mutableStateOf("한식") }

            val markerUiModels = list.value.map {
                when (selectCategory) {
                    "한식" -> KoreaMarkerUiModel(
                        name = it.placeName,
                        lat = it.y.toDouble(),
                        lng = it.x.toDouble(),
                        mapUrl = it.placeUrl
                    )
                    "중식" -> ChinaMarkerUiModel(
                        name = it.placeName,
                        lat = it.y.toDouble(),
                        lng = it.x.toDouble(),
                        mapUrl = it.placeUrl
                    )
                    "일식" -> JapanMarkerUiModel(
                        name = it.placeName,
                        lat = it.y.toDouble(),
                        lng = it.x.toDouble(),
                        mapUrl = it.placeUrl
                    )
                    "양식" -> WesternMarkerUiModel(
                        name = it.placeName,
                        lat = it.y.toDouble(),
                        lng = it.x.toDouble(),
                        mapUrl = it.placeUrl
                    )
                    "패스트 푸드" -> FastMarkerUiModel(
                        name = it.placeName,
                        lat = it.y.toDouble(),
                        lng = it.x.toDouble(),
                        mapUrl = it.placeUrl
                    )
                    "카페" -> DesertMarkerUiModel(
                        name = it.placeName,
                        lat = it.y.toDouble(),
                        lng = it.x.toDouble(),
                        mapUrl = it.placeUrl
                    )
                    else -> KoreaMarkerUiModel(
                        name = it.placeName,
                        lat = it.y.toDouble(),
                        lng = it.x.toDouble(),
                        mapUrl = it.placeUrl
                    )
                }
            }.toMutableStateList()

            LaunchedEffect(selectCategory) {
                val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
                fusedLocationProviderClient.lastLocation
                    .addOnSuccessListener { success: Location? ->
                        success?.let { location ->
                            searchMap(
                                query = selectCategory,
                                x = location.longitude.toDouble(),
                                y = location.latitude.toDouble(),
                                radius = 1000,
                                documents = list,
                            )
                        }
                    }
                    .addOnFailureListener { fail -> }
            }
            
            Column(Modifier.fillMaxSize()) {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxSize()) {
                    MapContents(
                        navController = navController,
                        restaurants = markerUiModels
                    )
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                    ) {
                        IconChipGroup(
                            elements = elements,
                            chipStyle = chipStyle,
                            onChipClicked = { content, isSelected, idx ->
                                if (!elements[idx].isSelected.value) {
                                    elements.forEach { it.isSelected.value = false }
                                    elements[idx].isSelected.value = !elements[idx].isSelected.value
                                    selectCategory = elements[idx].category
                                }
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier
                    .height(100.dp)
                    .align(Alignment.End))
            }
            
        }
    }
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapContents(
    navController: NavController,
    restaurants: List<RestaurantMarkerUiModel>
) {

    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        this.position = CameraPosition(LatLng.INVALID, 12.0)
    }

    var selectedRestaurant by remember {
        mutableStateOf<RestaurantMarkerUiModel?>(null)
    }

    NaverMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            minZoom = 6.0,
            locationTrackingMode = LocationTrackingMode.Follow
        ),
        locationSource = rememberLocationSource()
    ) {
        restaurants.forEach { restaurant ->
            Marker(
                state = MarkerState(position = LatLng(restaurant.lat, restaurant.lng)),
                captionText = restaurant.name,
                icon = restaurant.getMarkerIcon(),
                isHideCollidedSymbols = true,
                isHideCollidedCaptions = true,
                onClick = {
                    selectedRestaurant = restaurant
                    true
                }
            )
        }
    }
    val _customAlertDialogState: MutableState<CustomAlertDialogState> = remember { mutableStateOf(CustomAlertDialogState()) }
    val customAlertDialogState = _customAlertDialogState.value

    var showDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    LaunchedEffect(selectedRestaurant) {
        scope.launch {
            if (selectedRestaurant != null) {
                cameraPositionState.animate(
                    update = CameraUpdate.scrollAndZoomTo(
                        LatLng(selectedRestaurant!!.lat, selectedRestaurant!!.lng),
                        max(cameraPositionState.position.zoom, 16.0),
                    ),
                    animation = CameraAnimation.Fly,
                )
                _customAlertDialogState.value = CustomAlertDialogState("'${selectedRestaurant!!.name}' 음식점",
                    placeUrl = selectedRestaurant!!.mapUrl)
                showDialog = true
            } else {
                cameraPositionState.animate(
                    update = CameraUpdate.zoomTo(
                        min(cameraPositionState.position.zoom, 12.0),
                    ),
                    animation = CameraAnimation.Easing,
                )
            }
        }
    }


    if (showDialog) {
        CustomAlertDialog(
            customAlertDialogState.title,
            customAlertDialogState.subTitle,
            customAlertDialogState.description,
            {
                showDialog = false
        }, {
            navController.navigate("restaurant?url=${customAlertDialogState.placeUrl}")
            })
    }
}


private object RestaurantMarkerIcons {
    val Korea = OverlayImage.fromResource(R.drawable.foodcategory_1_color)
    val China = OverlayImage.fromResource(R.drawable.foodcategory_2_color)
    val Japan = OverlayImage.fromResource(R.drawable.foodcategory_3_color)
    val WESTERN = OverlayImage.fromResource(R.drawable.foodcategory_4_color)
    val FAST = OverlayImage.fromResource(R.drawable.foodcategory_5_color)
    val ASIAN = OverlayImage.fromResource(R.drawable.foodcategory_5_color)
    val DESERT = OverlayImage.fromResource(R.drawable.foodcategory_7_color)
}

private fun RestaurantMarkerUiModel.getMarkerIcon(): OverlayImage {
    return when (this) {
        is KoreaMarkerUiModel -> RestaurantMarkerIcons.Korea
        is ChinaMarkerUiModel -> RestaurantMarkerIcons.China
        is JapanMarkerUiModel -> RestaurantMarkerIcons.Japan
        is WesternMarkerUiModel -> RestaurantMarkerIcons.WESTERN
        is FastMarkerUiModel -> RestaurantMarkerIcons.FAST
        is DesertMarkerUiModel -> RestaurantMarkerIcons.DESERT
    }
}