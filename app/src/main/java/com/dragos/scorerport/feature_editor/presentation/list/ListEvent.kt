package com.dragos.scorerport.feature_editor.presentation.list

sealed class ListEvent {
    data class Order(val order: com.dragos.scorerport.feature_editor.domain.util.Order): ListEvent()
}
