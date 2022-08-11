package com.dragos.scorerport.impl.models

import androidx.compose.runtime.State

interface ItemState {
    fun get(item: ItemEnum.Strings): State<String>
    fun get(item: ItemEnum.Booleans): State<Boolean>
    fun get(item: ItemEnum.Ints): State<Int>
    fun get(item: ItemEnum.Counters): State<Int>

    fun set(item: ItemEnum.Strings, value: String)
    fun set(item: ItemEnum.Booleans, value: Boolean)
    fun set(item: ItemEnum.Ints, value: Int)
    fun set(item: ItemEnum.Counters, add: Int)

    fun getVisibility(item: ItemEnum): State<Boolean>
}