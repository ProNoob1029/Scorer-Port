package com.dragos.scorerport.feature_editor.domain.util

sealed class MatchOrder(val orderType: OrderType) {
    class Name(orderType: OrderType): MatchOrder(orderType)
    class Date(orderType: OrderType): MatchOrder(orderType)
    class Points(orderType: OrderType): MatchOrder(orderType)

    fun copy(orderType: OrderType): MatchOrder {
        return when(this) {
            is Name -> Name(orderType)
            is Date -> Date(orderType)
            is Points -> Points(orderType)
        }
    }
}
