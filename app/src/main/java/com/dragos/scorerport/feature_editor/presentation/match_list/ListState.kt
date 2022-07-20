package com.dragos.scorerport.feature_editor.presentation.match_list

import com.dragos.scorerport.feature_editor.domain.model.MatchDisplay
import com.dragos.scorerport.feature_editor.domain.util.MatchOrder
import com.dragos.scorerport.feature_editor.domain.util.OrderType

data class ListState(
    val matchList: List<MatchDisplay> = emptyList(),
    val matchOrder: MatchOrder = MatchOrder.Date(OrderType.Descending),
)
