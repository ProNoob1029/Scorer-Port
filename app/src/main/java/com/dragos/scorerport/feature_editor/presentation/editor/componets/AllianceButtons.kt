package com.dragos.scorerport.feature_editor.presentation.editor.componets

import android.view.HapticFeedbackConstants
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.presentation.editor.EditorViewModel
import com.dragos.scorerport.feature_editor.presentation.util.MeasureView
import com.dragos.scorerport.impl.freightfrenzy.MatchEnum

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllianceButtons (
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(
        top = 16.dp,
        start = 16.dp,
        end = 16.dp,
        bottom = 8.dp
    ),
    firstText: String,
    secondText: String,
    type: MatchEnum.Ints,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
    viewModel: EditorViewModel = hiltViewModel()
) {
    val activeIndex by rememberSaveable { viewModel.get(type) }

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

    Measure(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        firstText = firstText,
        secondText = secondText,
        textStyle = textStyle,
    ) { newModifier, vertical ->
        Surface(
            modifier = newModifier,
            color = redColor,
            onClick = {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                viewModel.set(
                    type,
                    value = if (activeIndex == 1) 0 else 1
                )
            },
            shape = MaterialTheme.shapes.large,
            contentColor = redTextColor,
        ) {
            Text(
                text = firstText,
                textAlign = TextAlign.Center,
                style = textStyle,
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
            modifier = newModifier,
            color = blueColor,
            onClick = {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                viewModel.set(
                    type,
                    value = if (activeIndex == 2) 0 else 2
                )
            },
            shape = MaterialTheme.shapes.large,
            contentColor = blueTextColor,
        ) {
            Text(
                text = secondText,
                textAlign = TextAlign.Center,
                style = textStyle,
                modifier = Modifier
                    .heightIn(min = 60.dp)
                    .padding(all = 8.dp)
                    .fillMaxHeight()
                    .wrapContentHeight(),
            )
        }
    }
}

@Composable
internal fun Measure (
    modifier: Modifier = Modifier,
    firstText: String,
    secondText: String,
    textStyle: TextStyle,
    content: @Composable (modifier: Modifier, vertical: Boolean) -> Unit
) {
    MeasureView(
        modifier = modifier,
        viewToMeasure = {
            Row(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Max)
            ) {
                Text(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .weight(1f),
                    text = firstText,
                    style = textStyle,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .weight(1f),
                    text = secondText,
                    style = textStyle,
                )
            }
        }
    ) { maxWidth, measuredWidth, _ ->
        val vertical = maxWidth < measuredWidth

        if (vertical) {
            Column {
                content(Modifier.fillMaxWidth(), true)
            }
        } else {
            Row(modifier = Modifier.height(IntrinsicSize.Max)) {
                content(Modifier.weight(1f), false)
            }
        }
    }
}