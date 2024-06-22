package kr.trueme.composetest.ui.domain.utils

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderType

sealed class FoodBundleOrder(val orderType: OrderType) {
    class Date(orderType: OrderType): FoodBundleOrder(orderType)
    class Like(orderType: OrderType): FoodBundleOrder(orderType)

}