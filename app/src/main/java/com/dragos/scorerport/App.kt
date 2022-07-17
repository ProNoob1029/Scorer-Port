package com.dragos.scorerport

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application(){
    lateinit var sharedPreferences: SharedPreferences

    var dynamicColorEnabled by mutableStateOf(false)

    fun initPreferences(activity: Activity){
        sharedPreferences = activity.getSharedPreferences("defaultPref", Context.MODE_PRIVATE)
        dynamicColorEnabled = sharedPreferences.getBoolean("dynamicColorEnabled", false)
    }
}