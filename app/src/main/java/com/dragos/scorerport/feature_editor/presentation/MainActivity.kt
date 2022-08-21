package com.dragos.scorerport.feature_editor.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.ui.Modifier
import com.dragos.scorerport.ScorerApp
import com.dragos.scorerport.ui.theme.ScorerPortTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    @Inject
    lateinit var scorerApp: ScorerApp

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScorerPortTheme(dynamicColor = scorerApp.dynamicColorEnabled) {
                //ListScreen()
                //EditScreen()
                //EditorScreen()

                //DestinationsNavHost(navGraph = NavGraphs.root)

                Scaffold {
                    Switch(
                        modifier = Modifier.padding(it),
                        checked = false,
                        onCheckedChange = {},
                        enabled = false
                    )
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