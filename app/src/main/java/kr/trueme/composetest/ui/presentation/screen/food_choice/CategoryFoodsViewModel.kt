package kr.trueme.composetest.ui.presentation.screen.food_choice

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.DefaultOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kr.trueme.composetest.ui.domain.model.FoodChoice
import kr.trueme.composetest.ui.domain.reposiotry.DefaultFoods
import kr.trueme.composetest.ui.domain.use_case.chioce.FoodChoiceUseCases
import javax.inject.Inject

@HiltViewModel
class CategoryFoodsViewModel @Inject constructor(
    private val foodChoiceUseCases: FoodChoiceUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(FoodChoicesState())
    val state: State<FoodChoicesState> = _state

    private var getFoodChoicesJob: Job? = null

    lateinit var categoryId: String

    init {
        savedStateHandle.get<String>("categoryId")?.let { categoryId ->
            this.categoryId = categoryId
            getFoodChoices(categoryId, DefaultOrder.Date(OrderType.Descending))
        }
    }


    fun onEvent(event: CategoryFoodsEvent) {
        when (event) {
            is CategoryFoodsEvent.SelectFood -> {
                viewModelScope.launch {

                }
            }
            is CategoryFoodsEvent.SelectCategory -> {
                viewModelScope.launch {

                }
            }

            else -> {

            }
        }
    }

    fun onCheckedChange(name: String, category: String, isChecked: Boolean) {
        viewModelScope.launch {

            val foodChoice = foodChoiceUseCases.getFoodChoice(name = name)
            if (foodChoice == null) {
                foodChoiceUseCases.addFoodChoice(
                    FoodChoice(name = name, category = category, isSelected = isChecked)
                )
            } else {
                foodChoiceUseCases.updateFoodChoice(
                    FoodChoice(name = name, category = category, isSelected = isChecked)
                )
            }

        }

    }

    private fun getFoodChoices(categoryId: String, defaultOrder: DefaultOrder) {
        getFoodChoicesJob?.cancel()
        getFoodChoicesJob = foodChoiceUseCases.getFoodChoicesByCategory(categoryId, defaultOrder)
            .onEach { foods ->
                val list = mutableListOf<FoodChoice>()
                DefaultFoods.getFoodsByCategory(categoryId).forEach {
                    list.add(FoodChoice(it.name, categoryId))
                }
                foods.forEach { food ->
                    val existingIndex = list.indexOfFirst { it.name == food.name }
                    if (existingIndex >= 0) {
                        list[existingIndex] = food
                    } else {
                        list.add(food)
                    }

                }
                _state.value = state.value.copy(
                    foods = list,
                    defaultOrder = defaultOrder
                )
            }
            .launchIn(viewModelScope)
    }
}