package com.dragos.scorerport.feature_editor.presentation.edit.components

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import com.dragos.scorerport.feature_editor.presentation.util.MeasureView

@Composable
fun TextSwitch (
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (checked: Boolean) -> Unit,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    colors: SwitchColors = SwitchDefaults.colors(),
    ) {
    val view = LocalView.current
    MeasureTextSwitch(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        text = text,
        textStyle = textStyle) { compact ->  
        if (compact) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = text,
                    style = textStyle
                )
                Switch(
                    modifier = Modifier.align(Alignment.End),
                    checked = checked,
                    onCheckedChange = {
                        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                        onCheckedChange(it)
                    },
                    colors = colors
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = text,
                    style = textStyle
                )
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                        onCheckedChange(it)
                    },
                    colors = colors
                )
            }
        }
    }
}

@Composable
internal fun MeasureTextSwitch (
    modifier: Modifier,
    checked: Boolean,
    onCheckedChange: (checked: Boolean) -> Unit,
    text: String,
    textStyle: TextStyle,
    content: @Composable (compact: Boolean) -> Unit
) {
    MeasureView(
        modifier = modifier,
        viewToMeasure =
        {
            Row {
                Text(text = text, style = textStyle, modifier = Modifier.width(IntrinsicSize.Min))
                Switch(checked = checked, onCheckedChange = onCheckedChange)
            }
        }
    ) { maxWidth, measuredWidth, _ ->
        val compact = maxWidth < measuredWidth
        content(compact = compact)
    }
}