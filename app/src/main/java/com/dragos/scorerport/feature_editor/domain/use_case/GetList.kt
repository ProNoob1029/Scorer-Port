package com.dragos.scorerport.feature_editor.domain.use_case

import com.dragos.scorerport.feature_editor.domain.model.ListItemModel
import com.dragos.scorerport.feature_editor.domain.repository.ListRepository
import com.dragos.scorerport.feature_editor.domain.util.Order
import com.dragos.scorerport.feature_editor.domain.util.OrderType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class GetList(
    repository: ListRepository
) {
    private val list = repository.getList()

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(
        order: Order = Order.Date(OrderType.Descending),
    ): Flow<List<ListItemModel>> {
        return list.mapLatest { list ->
            when(order.orderType) {
                is OrderType.Ascending -> {
                    when(order) {
                        is Order.Name -> list.sortedBy { it.title.lowercase() }
                        is Order.Date -> list.sortedBy { it.timeStamp }
                        is Order.Points -> list.sortedBy { it.points }
                    }
                }
                is OrderType.Descending -> {
                    when(order) {
                        is Order.Name -> list.sortedByDescending { it.title.lowercase() }
                        is Order.Date -> list.sortedByDescending { it.timeStamp }
                        is Order.Points -> list.sortedByDescending { it.points }
                    }
                }
            }
        }
    }
}