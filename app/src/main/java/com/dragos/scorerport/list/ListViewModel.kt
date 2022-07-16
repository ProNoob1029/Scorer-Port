package com.dragos.scorerport.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dragos.scorerport.Database
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    database: Database
): ViewModel() {
    var dynamicColorEnabled by mutableStateOf(false)

    val matchList = database.matchList
}