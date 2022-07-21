package com.dragos.scorerport.feature_editor.domain.model

import androidx.annotation.Keep

@Keep
data class ListItemModel(
    val title: String = "",
    val timeStamp: Long = 0,
    val points: Int = 0,
    val key: String = "",
)