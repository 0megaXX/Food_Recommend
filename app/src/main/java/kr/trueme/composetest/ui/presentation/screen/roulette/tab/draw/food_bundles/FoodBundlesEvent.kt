package kr.trueme.composetest.ui.presentation.screen.roulette.tab.draw.food_bundles

import kr.trueme.composetest.ui.domain.model.foodbundle.FoodBundle
import kr.trueme.composetest.ui.domain.utils.FoodBundleOrder

sealed class FoodBundlesEvent {
    data class Order(val foodBundleOrder: FoodBundleOrder): FoodBundlesEvent()
    data class DeleteFoodBundle(val foodBundle: FoodBundle): FoodBundlesEvent()
}