package com.dragos.scorerport.feature_editor.presentation.edit.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.dragos.scorerport.feature_editor.presentation.util.MeasureView
import com.dragos.scorerport.feature_editor.presentation.util.SegmentedButton

@Composable
fun TextButtons (
    modifier: Modifier = Modifier,
    items: List<String>,
    label: String,
    selectedIndex: Int?,
    onItemClick: (index: Int) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge
) {
    Measurements(
        modifier = modifier,
        items = items,
        label = label,
        textStyle = textStyle
    ) { inColumn, compactButton ->
        if (inColumn) {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextButtonsComponents(
                    items = items,
                    label = label,
                    selectedIndex = selectedIndex,
                    onItemClick = onItemClick,
                    compactButton = compactButton,
                    modifier = Modifier.align(Alignment.Start),
                    buttonModifier = Modifier.align(Alignment.End),
                    textStyle = textStyle
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButtonsComponents(
                    items = items,
                    label = label,
                    selectedIndex = selectedIndex,
                    onItemClick = onItemClick,
                    compactButton = compactButton,
                    modifier = Modifier.weight(1f),
                    textStyle = textStyle
                )
            }
        }
    }
}

@Composable
fun TextButtonsComponents(
    items: List<String>,
    label: String,
    selectedIndex: Int?,
    onItemClick: (index: Int) -> Unit,
    compactButton: Boolean,
    modifier: Modifier = Modifier,
    buttonModifier: Modifier = Modifier,
    textStyle: TextStyle
) {
    Text(
        modifier = modifier,
        text = label,
        style = textStyle
    )
    SegmentedButton(
        modifier = buttonModifier,
        items = items,
        selectedIndex = selectedIndex,
        onItemClick = onItemClick,
        vertical = compactButton,
    )
}

@Composable
internal fun Measurements(
    modifier: Modifier = Modifier,
    items: List<String>,
    label: String,
    textStyle: TextStyle,
    content: @Composable (inColumn: Boolean, compactButton: Boolean) -> Unit
) {
    MeasureView(
        modifier = modifier,
        viewToMeasure = listOf(
            { Text(text = label, style = textStyle, modifier = Modifier.width(IntrinsicSize.Min)) },
            { SegmentedButton(items = items) },
            { SegmentedButton(items = items, vertical = true) }
        )
    ) { maxWidth, measuredWidths, _ ->
        val textWidth = measuredWidths[0]
        val buttonWidth = measuredWidths[1]
        val compactButtonWidth = measuredWidths[2]
        when {
            maxWidth >= textWidth + buttonWidth ->
                content(inColumn = false, compactButton = false)
            maxWidth >= buttonWidth ->
                content(inColumn = true, compactButton = false)
            maxWidth >= textWidth + compactButtonWidth ->
                content(inColumn = false, compactButton = true)
            else ->
                content(inColumn = true, compactButton = true)
        }
    }
}