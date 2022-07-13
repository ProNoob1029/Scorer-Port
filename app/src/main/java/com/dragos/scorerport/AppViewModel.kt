package com.dragos.scorerport

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AppViewModel: ViewModel() {
    var dynamicColorEnabled by mutableStateOf(false)
}