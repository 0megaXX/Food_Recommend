package kr.trueme.composetest.ui.presentation.screen.roulette.tab.draw.food_bundles

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kr.trueme.composetest.ui.domain.model.FoodChoice
import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle
import kr.trueme.composetest.ui.domain.use_case.foodbundle.FoodBundleUseCases
import kr.trueme.composetest.ui.domain.utils.FoodBundleOrder
import javax.inject.Inject

@HiltViewModel
class FoodBundlesViewModel @Inject constructor(
    private val foodBundleUseCases: FoodBundleUseCases
) : ViewModel() {

    private val _state = mutableStateOf(FoodBundlesState())
    val state: State<FoodBundlesState> = _state

    private var recentlyDeletedNote: FoodBundle? = null

    private var getFoodBundlesJob: Job? = null

    init {

        getFoodBundles(FoodBundleOrder.Date(OrderType.Descending))

    }

    fun onEvent(event: FoodBundlesEvent) {
        when (event) {
            is FoodBundlesEvent.Order -> {
                if (state.value.foodBundleOrder::class == event.foodBundleOrder::class &&
                    state.value.foodBundleOrder.orderType == event.foodBundleOrder.orderType
                ) {
                    return
                }
                getFoodBundles(event.foodBundleOrder)
            }

            is FoodBundlesEvent.DeleteFoodBundle -> {
                viewModelScope.launch {
                    foodBundleUseCases.deleteFoodBundle(event.foodBundle)
                    recentlyDeletedNote = event.foodBundle
                }
            }

            else -> {}
        }
    }

    fun onCheckedChange(name: String, isChecked: Boolean) {
        viewModelScope.launch {
            foodBundleUseCases.updateFoodBundleIsLikeByTitle(name, isChecked)
        }

    }

    private fun getFoodBundles(foodBundleOrder: FoodBundleOrder) {
        getFoodBundlesJob?.cancel()
        getFoodBundlesJob = foodBundleUseCases.getFoodBundles(foodBundleOrder)
            .onEach { foodBundles ->
                _state.value = state.value.copy(
                    foodBundles = foodBundles,
                    foodBundleOrder = foodBundleOrder
                )
            }
            .launchIn(viewModelScope)
    }
}