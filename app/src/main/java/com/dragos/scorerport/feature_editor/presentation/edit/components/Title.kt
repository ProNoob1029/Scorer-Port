package com.dragos.scorerport.feature_editor.presentation.edit.components

import android.view.HapticFeedbackConstants
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Title(
    modifier: Modifier = Modifier,
    surfaceColor: Color = MaterialTheme.colorScheme.primaryContainer,
) {
    val view = LocalView.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            color = surfaceColor,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "New Match",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
            )
        }

        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            var text1 by rememberSaveable { mutableStateOf("") }
            //var text2 by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text1,
                onValueChange = { text1 = it },
                placeholder = {
                    Text(
                        text = "Title",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                textStyle = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            /*TextField(
                modifier = Modifier.fillMaxWidth(),
                value = text2,
                onValueChange = { text2 = it },
                placeholder = {
                    Text(
                        text = "Title",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                textStyle = MaterialTheme.typography.titleLarge,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
            )

            Spacer(modifier = Modifier.height(8.dp))*/

            Row(
                modifier = Modifier
            ) {

                var redActive by rememberSaveable { mutableStateOf(false) }
                var blueActive by rememberSaveable { mutableStateOf(false) }
                val redColor by animateColorAsState(
                    targetValue = if (redActive) Color.Red else MaterialTheme.colorScheme.secondaryContainer
                )
                val blueColor by animateColorAsState(
                    targetValue = if (blueActive) Color.Blue else MaterialTheme.colorScheme.secondaryContainer
                )
                val redTextColor by animateColorAsState(
                    targetValue = if (redActive) Color.White else LocalContentColor.current
                )
                val blueTextColor by animateColorAsState(
                    targetValue = if (blueActive) Color.White else LocalContentColor.current
                )

                Surface(
                    modifier = Modifier
                        .weight(1f),
                    color = redColor,
                    checked = redActive,
                    onCheckedChange = { isChecked ->
                        println("RED CHANGE")
                        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                        if(isChecked){
                            redActive = true
                            blueActive = false
                        } else {
                            redActive = false
                        }
                    },
                    shape = MaterialTheme.shapes.large,
                    contentColor = redTextColor,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 24.dp),
                        contentAlignment = Center,
                    ) {
                        Text(
                            text = "Red Alliance",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Surface(
                    modifier = Modifier
                        .weight(1f),
                    color = blueColor,
                    checked = blueActive,
                    onCheckedChange = { isChecked ->
                        println("BLUE CHANGE")
                        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                        if(isChecked){
                            blueActive = true
                            redActive = false
                        } else {
                            blueActive = false
                        }
                    },
                    shape = MaterialTheme.shapes.large,
                    contentColor = blueTextColor,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 24.dp),
                        contentAlignment = Center,
                    ) {
                        Text(
                            text = "Blue Alliance",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                        )
                    }
                }
            }
        }
    }
}