package com.dragos.scorerport.feature_editor.domain.util

sealed class Order(val orderType: OrderType) {
    class Name(orderType: OrderType): Order(orderType)
    class Date(orderType: OrderType): Order(orderType)
    class Points(orderType: OrderType): Order(orderType)

    fun copy(orderType: OrderType): Order {
        return when(this) {
            is Name -> Name(orderType)
            is Date -> Date(orderType)
            is Points -> Points(orderType)
        }
    }
}
