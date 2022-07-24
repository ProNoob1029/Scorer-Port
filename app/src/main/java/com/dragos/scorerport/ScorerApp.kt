package com.dragos.scorerport

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ScorerApp: Application(){
    lateinit var sharedPreferences: SharedPreferences

    var dynamicColorEnabled by mutableStateOf(true)

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = getSharedPreferences("defaultPref", Context.MODE_PRIVATE)
        //dynamicColorEnabled = sharedPreferences.getBoolean("dynamicColorEnabled", false)
    }
}