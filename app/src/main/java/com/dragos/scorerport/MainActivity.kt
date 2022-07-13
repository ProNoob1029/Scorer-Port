package com.dragos.scorerport

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dragos.scorerport.ui.theme.ScorerPortTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = viewModel<AppViewModel>()
            ScorerPortTheme(dynamicColor = viewModel.dynamicColorEnabled) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Conversation(nr = 100, onAction = {
                        viewModel.dynamicColorEnabled = !viewModel.dynamicColorEnabled
                    })
                }
            }
        }
    }
}



@Composable
fun MatchCard(
    match: MatchDisplay,
    index: Int,
    onClick: () -> Unit,
){
    Box(modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)) {
        Surface(
            shape = MaterialTheme.shapes.large,
            shadowElevation = 2.dp,
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .clip(shape = MaterialTheme.shapes.large)
                    .clickable {onClick()},
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.large,
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = (index + 1).toString().plus(". ").plus(match.name),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .weight(2f)
                    )

                    Column(
                        horizontalAlignment = Alignment.End
                    ){
                        Text(
                            text = match.time,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Text(
                            text = match.points,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Conversation(
    nr: Int,
    onAction: () -> Unit,
){
    LazyColumn {
        items(nr){
            index ->
            MatchCard(
                match = MatchDisplay(
                    "Phoenix",
                    "12pm",
                    "135 points"
                ),
                index,
                onClick = {onAction()}
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
            Conversation(nr = 20, onAction = {})
        }
    }
}

data class MatchDisplay(val name: String, val time: String, var points: String)