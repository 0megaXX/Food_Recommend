package kr.trueme.composetest.ui.presentation.screen.find_category_list

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kr.trueme.composetest.ui.domain.model.foodbundle.CustomFood
import kr.trueme.composetest.ui.domain.use_case.customfood.CustomFoodUseCases
import javax.inject.Inject

@HiltViewModel
class CustomFoodViewModel @Inject constructor(
    private val customFoodUseCases: CustomFoodUseCases
): ViewModel() {

    private var _customFoodList = mutableStateListOf<CustomFood>()
    val customFoodList: List<CustomFood> get() = _customFoodList

    init {
        getCustomFoods()
    }

    fun addCustomFood(customFood: CustomFood) {
        viewModelScope.launch {
            customFoodUseCases.addCustomFood(customFood)
        }
    }

    private fun getCustomFoods() {
       customFoodUseCases.getCustomFoods()
            .onEach { customFoods ->
                _customFoodList = customFoods.toMutableStateList()
            }
            .launchIn(viewModelScope)
    }
}