package com.dragos.scorerport.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.App
import com.dragos.scorerport.ui.theme.ScorerPortTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    @Inject
    lateinit var app: App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences: SharedPreferences = getSharedPreferences("defaultPref", Context.MODE_PRIVATE)
        app.dynamicColorEnabled = sharedPreferences.getBoolean("dynamicColorEnabled", false)
        setContent {
            val viewModel = hiltViewModel<ListViewModel>()
            val haptic = LocalHapticFeedback.current
            ScorerPortTheme(dynamicColor = app.dynamicColorEnabled) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MatchList(
                        viewModel.database.matchList,
                        onClick = {
                            app.dynamicColorEnabled = true
                            sharedPreferences.edit().putBoolean("dynamicColorEnabled", true).apply()
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        },
                        onHold = {
                            app.dynamicColorEnabled = false
                            sharedPreferences.edit().putBoolean("dynamicColorEnabled", false).apply()
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        },
                    )
                }
            }
        }
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