package com.dragos.scorerport.feature_editor.presentation.edit

import androidx.lifecycle.ViewModel
import com.dragos.scorerport.feature_editor.domain.model.ItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    val state: ItemState,
    val screen: Screen
) : ViewModel()