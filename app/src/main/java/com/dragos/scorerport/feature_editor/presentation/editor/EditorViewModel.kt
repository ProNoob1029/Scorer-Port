package com.dragos.scorerport.feature_editor.presentation.editor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragos.scorerport.feature_editor.domain.model.Match
import com.dragos.scorerport.feature_editor.domain.use_case.MatchUseCases
import com.dragos.scorerport.feature_editor.presentation.navArgs
import com.dragos.scorerport.feature_editor.presentation.util.autoId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val matchUseCases: MatchUseCases,
): ViewModel() {
    val key: String? = savedStateHandle.navArgs<EditorArgs>().key

    private var startMatch: Match? = null

    val state = NewState()

    private val _editEnabled = mutableStateOf(false)
    val editEnabled: State<Boolean> = _editEnabled

    fun setEnabled(enabled: Boolean) {
        _editEnabled.value = enabled
    }

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        if (key != null) {
            viewModelScope.launch {
                startMatch = matchUseCases.getMatch(key)

                if (startMatch != null) {
                    state.set(startMatch!!)
                }
            }
        }
    }

    fun reset() = state.set(Match())

    fun save() {
        viewModelScope.launch {
            matchUseCases.saveMatch(
                if (startMatch == null)
                    state.createMatch(Match(
                        key = autoId(),
                        createStamp = System.currentTimeMillis(),
                        editStamp = System.currentTimeMillis(),
                        version = 1
                    ))
                else state.createMatch(startMatch!!.copy(
                    editStamp = System.currentTimeMillis(),
                    version = startMatch!!.version + 1
                ))
            )

            _eventFlow.emit(UiEvent.NavigateUp)
        }
    }

    sealed class UiEvent {
        object NavigateUp: UiEvent()
    }
}