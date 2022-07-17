package com.dragos.scorerport.list

import androidx.lifecycle.ViewModel
import com.dragos.scorerport.Database
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    val database: Database,
): ViewModel()