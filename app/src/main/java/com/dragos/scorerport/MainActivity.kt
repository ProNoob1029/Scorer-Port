package com.dragos.scorerport

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dragos.scorerport.ui.theme.ScorerPortTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<AppViewModel>()
            val haptic = LocalHapticFeedback.current
            ScorerPortTheme(dynamicColor = viewModel.dynamicColorEnabled) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Conversation(viewModel.matchList){
                        viewModel.dynamicColorEnabled = !viewModel.dynamicColorEnabled
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    }
                }
                viewModel.message.observe(this) { event ->
                    event.getContentIfNotHandled()?.let {
                        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}

@Composable
fun Conversation(
    matchList: List<MatchDisplay>,
    onAction: () -> Unit,
) {
    LazyColumn {
        itemsIndexed(
            items = matchList,
            key = { _, matchDisplay: MatchDisplay ->
                matchDisplay.key
            }
        ) { index: Int , matchDisplay: MatchDisplay ->
            MatchCard(
                match = matchDisplay,
                index = index,
                onClick = {onAction()},
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
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
}