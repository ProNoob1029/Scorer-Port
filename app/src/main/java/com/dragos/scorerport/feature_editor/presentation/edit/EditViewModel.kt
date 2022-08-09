package com.dragos.scorerport.feature_editor.presentation.edit

import androidx.lifecycle.ViewModel
import com.dragos.scorerport.feature_editor.domain.model.MatchModel
import com.dragos.scorerport.feature_editor.domain.model.MatchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor() : ViewModel() {

    val state = MatchState(MatchModel())

}