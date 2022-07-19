package com.dragos.scorerport.feature_editor.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalHapticFeedback
import com.dragos.scorerport.ScorerApp
import com.dragos.scorerport.feature_editor.presentation.match_list.MainActivityCompose
import com.dragos.scorerport.ui.theme.ScorerPortTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    @Inject
    lateinit var scorerApp: ScorerApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val hapticContext = LocalHapticFeedback.current
            ScorerPortTheme(dynamicColor = scorerApp.dynamicColorEnabled) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainActivityCompose(hapticContext = hapticContext)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        scorerApp.sharedPreferences.edit().putBoolean("dynamicColorEnabled", scorerApp.dynamicColorEnabled).apply()
    }

    /*@Preview(showBackground = true, name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun DefaultPreview() {
        ScorerPortTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                //Conversation(onAction = {})
            }
        }
    }*/
}