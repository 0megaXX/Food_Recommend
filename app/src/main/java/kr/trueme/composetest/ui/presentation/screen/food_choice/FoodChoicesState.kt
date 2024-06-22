package kr.trueme.composetest.ui.presentation.screen.food_choice

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.DefaultOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import kr.trueme.composetest.ui.domain.model.FoodChoice

data class FoodChoicesState(
    val foods: List<FoodChoice> = emptyList(),
    val defaultOrder: DefaultOrder = DefaultOrder.Date(OrderType.Descending),
) {
}

data class FoodChoiceState(
    val text: String,
    var isChecked: MutableState<Boolean> = mutableStateOf(false),
) {

}