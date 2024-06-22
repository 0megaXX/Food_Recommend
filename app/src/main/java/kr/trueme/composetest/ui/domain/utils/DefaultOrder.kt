package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util

sealed class DefaultOrder(val orderType: OrderType) {
    class Date(orderType: OrderType): DefaultOrder(orderType)
    class Color(orderType: OrderType): DefaultOrder(orderType)

    fun copy(orderType: OrderType): DefaultOrder {
        return when(this) {
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}
