package com.dragos.scorerport.feature_editor.presentation.match_list

import androidx.compose.foundation.lazy.LazyListState
import com.dragos.scorerport.feature_editor.domain.model.MatchDisplay
import com.dragos.scorerport.feature_editor.domain.util.MatchOrder
import com.dragos.scorerport.feature_editor.domain.util.OrderType

data class MatchListState(
    val matchList: List<MatchDisplay> = emptyList(),
    val matchOrder: MatchOrder = MatchOrder.Date(OrderType.Descending),
    val listState: LazyListState = LazyListState(0, 0)
)
