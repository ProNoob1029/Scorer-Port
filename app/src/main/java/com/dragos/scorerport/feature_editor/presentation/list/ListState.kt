package com.dragos.scorerport.feature_editor.presentation.list

import com.dragos.scorerport.feature_editor.domain.model.ListItemModel
import com.dragos.scorerport.feature_editor.domain.util.Order
import com.dragos.scorerport.feature_editor.domain.util.OrderType

data class ListState(
    val list: List<ListItemModel> = emptyList(),
    val order: Order = Order.Date(OrderType.Descending),
    val listLocation: String = "test",
)
