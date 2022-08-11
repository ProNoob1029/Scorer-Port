package com.dragos.scorerport.feature_editor.presentation.edit

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.dragos.scorerport.impl.models.ItemEnum
import com.dragos.scorerport.impl.models.ItemState
import com.dragos.scorerport.impl.models.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val state: ItemState,
    val screen: Screen
) : ViewModel() {

    fun getState(item: ItemEnum.Strings): State<String> = state.get(item)
    fun getState(item: ItemEnum.Booleans): State<Boolean> = state.get(item)
    fun getState(item: ItemEnum.Ints): State<Int> = state.get(item)
    fun getState(item: ItemEnum.Counters): State<Int> = state.get(item)

    fun setState(item: ItemEnum.Strings, value: String) = state.set(item, value)
    fun setState(item: ItemEnum.Booleans, value: Boolean) = state.set(item, value)
    fun setState(item: ItemEnum.Ints, value: Int) = state.set(item, value)
    fun setState(item: ItemEnum.Counters, add: Int) = state.set(item, add)

    fun getVisibility(item: ItemEnum) = state.getVisibility(item)
}