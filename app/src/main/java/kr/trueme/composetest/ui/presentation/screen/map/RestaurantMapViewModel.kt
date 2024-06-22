package kr.trueme.composetest.ui.presentation.screen.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RestaurantMapViewModel(

) : ViewModel() {

    var uiModel by mutableStateOf<List<RestaurantMarkerUiModel>>(emptyList())
        private set

    init {
        viewModelScope.launch {

//            uiModel = listOf(
//                KoreaMarkerUiModel(areaCode = "a", code = "code", name = "테스트", 127.024612, 37.532600),
//                KoreaMarkerUiModel(areaCode = "a", code = "code", name = "테스트2", 128.024612, 37.532600)
//            )

//            uiModel = list.value.map {
//                KoreaMarkerUiModel(
//                    areaCode = "areacode",
//                    code = "code",
//                    name = it.placeName,
//                    lat = it.y.toDouble(),
//                    lng = it.x.toDouble()
//                )
//            }.toMutableStateList()
        }
    }

    fun onCategorySelect() {
        uiModel = listOf(
            KoreaMarkerUiModel(name = "ㅁㅁ", 127.024612, 37.532600, ""),
            KoreaMarkerUiModel(name = "ㅎㅎ", 127.524612, 37.532600, "")
        )
    }
}