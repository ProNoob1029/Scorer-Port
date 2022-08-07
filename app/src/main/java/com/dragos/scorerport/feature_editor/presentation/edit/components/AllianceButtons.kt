package com.dragos.scorerport.feature_editor.presentation.edit.components

import android.view.HapticFeedbackConstants
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.feature_editor.presentation.util.MeasureView

@Composable
fun AllianceButtons(
    modifier: Modifier = Modifier,
    fontStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    redText: String = "Red Alliance",
    blueText: String = "Blue Alliance",
    activeIndex: Int?,
    onItemClick: (index: Int) -> Unit,
) {
    MeasureView(
        modifier = modifier,
        viewToMeasure = {
            MeasureHorizontalAllianceButtons(
                fontStyle = fontStyle,
                redText = redText,
                blueText = blueText
            )
    }) { maxWidth, measuredWidth, _ ->
        val compact = maxWidth < measuredWidth
        if (compact) {
            Column {
                AllianceButtonsInternal(
                    modifier = Modifier.fillMaxWidth(),
                    vertical = true,
                    redText = redText,
                    blueText = blueText,
                    fontStyle = fontStyle,
                    onClick = onItemClick,
                    activeIndex = activeIndex
                )
            }
        } else {
            Row(modifier = Modifier.height(IntrinsicSize.Max)) {
                AllianceButtonsInternal(
                    modifier = Modifier.weight(1f),
                    redText = redText,
                    blueText = blueText,
                    fontStyle = fontStyle,
                    onClick = onItemClick,
                    activeIndex = activeIndex
                )
            }
        }
    }
}

@Composable
internal fun MeasureHorizontalAllianceButtons(
    fontStyle: TextStyle,
    redText: String,
    blueText: String,
) {
    Row(
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Max)
    ) {
        Surface(
            modifier = Modifier
                .weight(1f),
        ) {
            Text(
                text = redText,
                textAlign = TextAlign.Center,
                style = fontStyle,
                maxLines = 2,
                modifier = Modifier
                    .heightIn(min = 60.dp)
                    .padding(all = 8.dp)
                    .fillMaxHeight()
                    .wrapContentHeight(),
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Surface(
            modifier = Modifier
                .weight(1f),
        ) {
            Text(
                text = blueText,
                textAlign = TextAlign.Center,
                style = fontStyle,
                maxLines = 2,
                modifier = Modifier
                    .heightIn(min = 50.dp)
                    .padding(all = 8.dp)
                    .fillMaxHeight()
                    .wrapContentHeight(),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AllianceButtonsInternal(
    modifier: Modifier = Modifier,
    vertical: Boolean = false,
    fontStyle: TextStyle,
    redText: String,
    blueText: String,
    onClick: (index: Int) -> Unit,
    activeIndex: Int?
) {
    val redActive = activeIndex == 1
    val blueActive = activeIndex == 2
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

    val view = LocalView.current

    Surface(
        modifier = modifier,
        color = redColor,
        onClick = {
            view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            onClick(1)
                  },
        shape = MaterialTheme.shapes.large,
        contentColor = redTextColor,
    ) {
        Text(
            text = redText,
            textAlign = TextAlign.Center,
            style = fontStyle,
            modifier = Modifier
                .heightIn(min = 60.dp)
                .padding(all = 8.dp)
                .fillMaxHeight()
                .wrapContentHeight(),
        )
    }

    if(vertical)
        Spacer(modifier = Modifier.height(8.dp))
    else
        Spacer(modifier = Modifier.width(16.dp))

    Surface(
        modifier = modifier,
        color = blueColor,
        onClick = {
            view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            onClick(2)
                  },
        shape = MaterialTheme.shapes.large,
        contentColor = blueTextColor,
    ) {
        Text(
            text = blueText,
            textAlign = TextAlign.Center,
            style = fontStyle,
            modifier = Modifier
                .heightIn(min = 60.dp)
                .padding(all = 8.dp)
                .fillMaxHeight()
                .wrapContentHeight(),
        )
    }
}