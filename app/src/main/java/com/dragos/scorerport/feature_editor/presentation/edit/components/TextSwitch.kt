package com.dragos.scorerport.feature_editor.presentation.edit.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.dragos.scorerport.feature_editor.presentation.util.MeasureViewWidth

@Composable
fun TextSwitch (
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (checked: Boolean) -> Unit,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge
) {
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
                    onCheckedChange = onCheckedChange
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
                    onCheckedChange = onCheckedChange
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
    MeasureViewWidth(
        modifier = modifier,
        viewToMeasure = {
            Row {
               Text(text = text, style = textStyle, modifier = Modifier.width(IntrinsicSize.Min))
               Switch(checked = checked, onCheckedChange = onCheckedChange) 
            }
        }
    ) { maxWidth, measuredWidth ->  
        content(compact = measuredWidth > maxWidth)
    }
}