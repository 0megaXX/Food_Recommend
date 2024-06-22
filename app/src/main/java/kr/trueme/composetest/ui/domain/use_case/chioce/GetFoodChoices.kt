package kr.trueme.composetest.ui.domain.use_case.chioce

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.DefaultOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.trueme.composetest.ui.domain.model.FoodChoice
import kr.trueme.composetest.ui.domain.reposiotry.FoodChoiceRepository

class GetFoodChoices(
    private val repository: FoodChoiceRepository
) {

    operator fun invoke(
        defaultOrder: DefaultOrder = DefaultOrder.Date(OrderType.Descending)
    ) : Flow<List<FoodChoice>> {
        return repository.getFoodChoices().map { foodChoices ->
            when(defaultOrder.orderType) {
                is OrderType.Ascending -> {
                    when(defaultOrder) {
                        is DefaultOrder.Date -> foodChoices.sortedBy { it.name.lowercase() }
                        is DefaultOrder.Color -> foodChoices.sortedBy { it.name.lowercase() }
                    }
                }
                is OrderType.Descending -> {
                    when(defaultOrder) {
                        is DefaultOrder.Date -> foodChoices.sortedByDescending { it.name.lowercase() }
                        is DefaultOrder.Color -> foodChoices.sortedByDescending { it.name.lowercase() }
                    }
                }
            }
        }
    }
}