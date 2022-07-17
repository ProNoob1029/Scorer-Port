package com.dragos.scorerport.list

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
import com.dragos.scorerport.ui.theme.ScorerPortTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<ListViewModel>()
            viewModel.app.initPreferences(this)
            val haptic = LocalHapticFeedback.current
            ScorerPortTheme(dynamicColor = viewModel.app.dynamicColorEnabled) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MatchList(
                        viewModel.database.matchList,
                        onClick = {
                            viewModel.app.dynamicColorEnabled = true
                            viewModel.app.sharedPreferences.edit()
                                .putBoolean("dynamicColorEnabled", true).apply()
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        },
                        onHold = {
                            viewModel.app.dynamicColorEnabled = false
                            viewModel.app.sharedPreferences.edit()
                                .putBoolean("dynamicColorEnabled", false).apply()
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