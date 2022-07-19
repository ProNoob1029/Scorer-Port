package com.dragos.scorerport.feature_editor.presentation.match_list

import com.dragos.scorerport.feature_editor.domain.util.MatchOrder

sealed class ListEvent {
    data class Order(val matchOrder: MatchOrder): ListEvent()
}
