package com.dragos.scorerport.feature_editor.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dragos.scorerport.ScorerApp
import com.dragos.scorerport.feature_editor.presentation.edit.EditScreen
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
            ScorerPortTheme(dynamicColor = scorerApp.dynamicColorEnabled) {
                //ListScreen()
                EditScreen()
                //EditorScreen()
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