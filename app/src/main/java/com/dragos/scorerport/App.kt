package com.dragos.scorerport

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App(
): Application(){
    var dynamicColorEnabled by mutableStateOf(false)
}