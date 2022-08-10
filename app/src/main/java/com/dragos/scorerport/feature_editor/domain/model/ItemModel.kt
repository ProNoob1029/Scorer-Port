package com.dragos.scorerport.feature_editor.domain.model

import androidx.compose.runtime.State

interface ItemState {

    fun getString(item: ItemEnum): State<String>
    fun getBoolean(item: ItemEnum): State<Boolean>
    fun getInt(item: ItemEnum): State<Int>

    fun setString(item: ItemEnum, value: String)
    fun setBoolean(item: ItemEnum, value: Boolean)
    fun setInt(item: ItemEnum, value: Int)

    fun getVisibility(item: ItemEnum): State<Boolean>
}

interface ItemEnum