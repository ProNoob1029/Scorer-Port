package com.dragos.scorerport.feature_editor.presentation.editor.componets

import android.view.HapticFeedbackConstants
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.presentation.editor.EditorViewModel
import com.dragos.scorerport.feature_editor.presentation.util.MeasureView
import com.dragos.scorerport.impl.freightfrenzy.MatchEnum

@Composable
fun TextSwitch (
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(top = 8.dp, start = 16.dp, end = 16.dp),
    text: String,
    type: MatchEnum.Booleans,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    viewModel: EditorViewModel = hiltViewModel()
) {
    val view = LocalView.current
    val checked by rememberSaveable { viewModel.get(type) }
    val animatedVisible by rememberSaveable { viewModel.getAnimatedVisibility(type) }
    val visible by rememberSaveable { viewModel.getVisibility(type) }
    val specialColor = rememberSaveable { viewModel.getSpecialColor(type) }
    val colors = if (specialColor)
        SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.onTertiary,
            checkedTrackColor = MaterialTheme.colorScheme.tertiary,
            checkedBorderColor = MaterialTheme.colorScheme.tertiary
        )
    else
        SwitchDefaults.colors()

    if (visible) {
        AnimatedVisibility(
            modifier = modifier.padding(paddingValues),
            visible = animatedVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Measure(
                text = text,
                textStyle = textStyle
            ) { modifier1, modifier2 ->
                Text(modifier = modifier1 ,text = text, style = textStyle, textAlign = TextAlign.Start)
                Switch(
                    modifier = modifier2,
                    checked = checked,
                    colors = colors,
                    onCheckedChange = {
                        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                        viewModel.set(type, it)
                    }
                )
            }
        }
    }

}

@Composable
internal fun Measure (
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle,
    content: @Composable (modifier: Modifier, modifier2: Modifier) -> Unit
) {
    MeasureView(
        modifier = modifier,
        viewToMeasure = {
            Row {
                Text(text = text, style = textStyle, modifier = Modifier.width(IntrinsicSize.Min))
                Switch(checked = false, onCheckedChange = {})
            }
        }
    ) { maxWidth, measuredWidth, _ ->
        val vertical = maxWidth < measuredWidth

        if (vertical) {
            Column {
                content(Modifier, Modifier.align(Alignment.End))
            }
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                content(Modifier.weight(1f), Modifier)
            }
        }
    }
}