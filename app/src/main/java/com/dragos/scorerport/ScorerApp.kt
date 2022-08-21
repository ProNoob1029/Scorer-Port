package com.dragos.scorerport

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ScorerApp @Inject constructor(): Application(){
    lateinit var sharedPreferences: SharedPreferences

    var dynamicColorEnabled by mutableStateOf(true)

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = getSharedPreferences("defaultPref", Context.MODE_PRIVATE)
        //dynamicColorEnabled = sharedPreferences.getBoolean("dynamicColorEnabled", false)
        Firebase.database.setPersistenceEnabled(false)
    }
}