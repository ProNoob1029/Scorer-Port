package com.dragos.scorerport.feature_editor.domain.use_case

import com.dragos.scorerport.feature_editor.domain.model.MatchDisplay
import com.dragos.scorerport.feature_editor.domain.repository.MatchListRepository
import com.dragos.scorerport.feature_editor.domain.util.MatchOrder
import com.dragos.scorerport.feature_editor.domain.util.OrderType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class GetMatchList(
    repository: MatchListRepository
) {
    private val matchList = repository.getMatchList()

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(
        matchOrder: MatchOrder = MatchOrder.Date(OrderType.Descending),
    ): Flow<List<MatchDisplay>> {
        return matchList.mapLatest { matchList ->
            when(matchOrder.orderType) {
                is OrderType.Ascending -> {
                    when(matchOrder) {
                        is MatchOrder.Name -> matchList.sortedBy { it.name.lowercase() }
                        is MatchOrder.Date -> matchList.sortedBy { it.timeStamp }
                        is MatchOrder.Points -> matchList.sortedBy { it.points }
                    }
                }
                is OrderType.Descending -> {
                    when(matchOrder) {
                        is MatchOrder.Name -> matchList.sortedByDescending { it.name.lowercase() }
                        is MatchOrder.Date -> matchList.sortedByDescending { it.timeStamp }
                        is MatchOrder.Points -> matchList.sortedByDescending { it.points }
                    }
                }
            }
        }
    }
}