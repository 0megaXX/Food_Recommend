package kr.trueme.composetest.ui.domain.use_case.foodbundle

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle
import kr.trueme.composetest.ui.domain.reposiotry.FoodBundleRepository
import kr.trueme.composetest.ui.domain.utils.FoodBundleOrder

class GetFoodBundles(
    private val repository: FoodBundleRepository
) {

    operator fun invoke(
        foodBundleOrder: FoodBundleOrder = FoodBundleOrder.Date(OrderType.Descending)
    ): Flow<List<FoodBundle>> {
        return repository.getFoodBundles().map { foodbundle ->
            when(foodBundleOrder.orderType) {
                is OrderType.Ascending -> {
                    when(foodBundleOrder) {
                        is FoodBundleOrder.Date -> foodbundle.sortedBy { it.timestamp }
                        is FoodBundleOrder.Like -> foodbundle.sortedBy { it.isLike }
                    }
                }
                is OrderType.Descending -> {
                    when(foodBundleOrder) {
                        is FoodBundleOrder.Date -> foodbundle.sortedByDescending { it.timestamp }
                        is FoodBundleOrder.Like -> foodbundle.sortedByDescending { it.isLike }
                    }
                }
            }
        }
    }
}