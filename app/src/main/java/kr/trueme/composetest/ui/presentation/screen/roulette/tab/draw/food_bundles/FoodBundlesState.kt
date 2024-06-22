package kr.trueme.composetest.ui.presentation.screen.roulette.tab.draw.food_bundles

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle
import kr.trueme.composetest.ui.domain.utils.FoodBundleOrder

data class FoodBundlesState(
    val foodBundles: List<FoodBundle> = emptyList(),
    val foodBundleOrder: FoodBundleOrder = FoodBundleOrder.Date(OrderType.Descending),
) {
}