package kr.trueme.composetest.ui.domain.use_case.chioce

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.DefaultOrder
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kr.trueme.composetest.ui.domain.model.FoodChoice
import kr.trueme.composetest.ui.domain.reposiotry.FoodChoiceRepository

class GetFoodChoicesByCategory(
    private val repository: FoodChoiceRepository
) {
    operator fun invoke(
        category: String,
        defaultOrder: DefaultOrder = DefaultOrder.Date(OrderType.Descending)
    ): Flow<List<FoodChoice>> {
        return repository.getFoodChoicesByCategory(category)
    }
}