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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.feature_editor.presentation.util.MeasureViewWidth

@Composable
fun AllianceButtons() {
    /*BoxWithConstraints {
        if(maxWidth > 290.dp) {
            Row(modifier = Modifier.height(IntrinsicSize.Max)) {
                AllianceButtonsInternal(modifier = Modifier.weight(1f))
            }
        } else {
            Column {
                AllianceButtonsInternal(compact = true)
            }
        }
    }*/

    HorizontalAllianceButtons()
}

@Composable
internal fun HorizontalAllianceButtons(
    fontStyle: TextStyle = MaterialTheme.typography.headlineSmall
) {
    Row(
        modifier = Modifier.width(IntrinsicSize.Min)
    ) {
        Surface(
            shape = MaterialTheme.shapes.large,
            color = Color.Blue,
        ) {
            Text(
                text = "Red Alliance",
                textAlign = TextAlign.Center,
                style = fontStyle,
                maxLines = 2,
                modifier = Modifier
                    .padding(all = 8.dp)
                    .heightIn(min = 50.dp)
                    .requiredWidthIn()
                    .weight(1f)
                    .wrapContentHeight(),
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Surface(
            shape = MaterialTheme.shapes.large,
            contentColor = Color.White,
        ) {
            Text(
                text = "Blue Alliance",
                textAlign = TextAlign.Center,
                style = fontStyle,
                maxLines = 2,
                modifier = Modifier
                    .padding(all = 8.dp)
                    .heightIn(min = 50.dp)
                    .weight(1f)

                    .wrapContentHeight(),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AllianceButtonsInternal(
    modifier: Modifier = Modifier,
    compact: Boolean = false,
    fontStyle: TextStyle = MaterialTheme.typography.headlineSmall
) {
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

    Surface(
        modifier = modifier,
        color = redColor,
        checked = redActive,
        onCheckedChange = { isChecked ->
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
        Text(
            text = "Red Alliance",
            textAlign = TextAlign.Center,
            style = fontStyle,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 8.dp)
                .heightIn(min = 50.dp)
                .wrapContentHeight(),
        )
    }

    if(compact)
        Spacer(modifier = Modifier.height(8.dp))
    else
        Spacer(modifier = Modifier.width(16.dp))

    Surface(
        modifier = modifier,
        color = blueColor,
        checked = blueActive,
        onCheckedChange = { isChecked ->
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
        Text(
            text = "Blue Alliance",
            textAlign = TextAlign.Center,
            style = fontStyle,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 8.dp)
                .heightIn(min = 50.dp)
                .wrapContentHeight(),
        )
    }
}