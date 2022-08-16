package com.dragos.scorerport.feature_editor.presentation.editor

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.dragos.scorerport.impl.freightfrenzy.MatchEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(

): ViewModel() {
    private val state = EditorState()

    fun get(type: MatchEnum.Strings): State<String> = state.get(type)
    fun get(type: MatchEnum.Ints): State<Int> = state.get(type)
    fun get(type: MatchEnum.Booleans): State<Boolean> = state.get(type)
    fun get(type: MatchEnum.Counters): State<Int> = state.get(type)

    fun set(type: MatchEnum.Strings, value: String) = state.set(type, value)
    fun set(type: MatchEnum.Booleans, value: Boolean) = state.set(type, value)
    fun set(type: MatchEnum.Ints, value: Int) = state.set(type, value)
    fun set(type: MatchEnum.Counters, add: Int) = state.set(type, add)
}