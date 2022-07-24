package com.dragos.scorerport.feature_editor.presentation.edit.components

import android.view.HapticFeedbackConstants
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllianceButtons() {
    val view = LocalView.current

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

    BoxWithConstraints {
        if(maxWidth > 290.dp) {
            Row(modifier = Modifier.height(IntrinsicSize.Max)) {
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
                            .fillMaxSize()
                            .padding(vertical = 8.dp, horizontal = 8.dp),
                        contentAlignment = Alignment.Center,
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
                            .fillMaxSize()
                            .padding(vertical = 8.dp, horizontal = 8.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Blue Alliance",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                        )
                    }
                }
            }
        } else {
            Column {
                Surface(
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
                            .fillMaxSize()
                            .padding(vertical = 8.dp, horizontal = 8.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Red Alliance",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
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
                            .fillMaxSize()
                            .padding(vertical = 8.dp, horizontal = 8.dp),
                        contentAlignment = Alignment.Center,
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