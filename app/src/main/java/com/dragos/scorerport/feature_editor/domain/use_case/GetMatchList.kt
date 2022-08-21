package com.dragos.scorerport.feature_editor.domain.use_case

import com.dragos.scorerport.feature_editor.domain.model.Match
import com.dragos.scorerport.feature_editor.domain.repository.Repository
import com.dragos.scorerport.feature_editor.domain.util.Order
import com.dragos.scorerport.feature_editor.domain.util.OrderType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class GetMatchList(
    repository: Repository
) {
    private val list = repository.getMatches()

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(
        order: Order = Order.Date(OrderType.Descending),
    ): Flow<List<Match>> {
        return list.mapLatest { list ->
            when(order.orderType) {
                is OrderType.Ascending -> {
                    when(order) {
                        is Order.Name -> list.sortedBy { it.title.lowercase() }
                        is Order.Date -> list.sortedBy { it.createStamp }
                        is Order.Points -> list.sortedBy { it.totalPoints }
                    }
                }
                is OrderType.Descending -> {
                    when(order) {
                        is Order.Name -> list.sortedByDescending { it.title.lowercase() }
                        is Order.Date -> list.sortedByDescending { it.createStamp }
                        is Order.Points -> list.sortedByDescending { it.totalPoints }
                    }
                }
            }
        }
    }
}