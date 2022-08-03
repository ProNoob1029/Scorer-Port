package com.dragos.scorerport.feature_editor.presentation.edit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dragos.scorerport.feature_editor.presentation.util.MeasureViewWidth
import com.dragos.scorerport.feature_editor.presentation.util.SegmentedButton

@Composable
fun TextButtons (
    modifier: Modifier = Modifier,
    items: List<String>,
    label: String,
    selectedIndex: Int?,
    onItemClick: (index: Int) -> Unit
) {
    Measurements(
        modifier = modifier,
        items = items,
        label = label
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
                    buttonModifier = Modifier.align(Alignment.End)
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
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }

    /*MeasureViewWidthInDp(
        modifier = modifier,
        viewToMeasure = {
            Text(
                text = label,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    ) { measuredWidthText, maxWidth ->
        MeasureViewWidthInDp(
            viewToMeasure = {
                SegmentedButton(
                    items = items,
                    selectedIndex = selectedIndex,
                    onItemClick = onItemClick,
                )
            }
        ) { measuredWidthButtons, _ ->
            if(measuredWidthText + measuredWidthButtons <= maxWidth) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.weight(1f),
                    )
                    SegmentedButton(
                        items = items,
                        selectedIndex = selectedIndex,
                        onItemClick = onItemClick,
                    )
                }
            } else if (measuredWidthButtons <= maxWidth) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.align(Alignment.Start),
                    )
                    SegmentedButton(
                        modifier = Modifier.align(Alignment.End),
                        items = items,
                        selectedIndex = selectedIndex,
                        onItemClick = onItemClick,
                    )
                }
            } else {
                MeasureViewWidthInDp(
                    viewToMeasure = {
                        SegmentedButton(
                            items = items,
                            vertical = true,
                            selectedIndex = selectedIndex,
                            onItemClick = onItemClick,
                        )
                    }
                ) { measuredWidthCompact, _ ->
                    if (measuredWidthCompact + measuredWidthText <= maxWidth) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.weight(1f),
                            )
                            SegmentedButton(
                                vertical = true,
                                items = items,
                                selectedIndex = selectedIndex,
                                onItemClick = onItemClick,
                            )
                        }
                    } else {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = label,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.align(Alignment.Start),
                            )
                            SegmentedButton(
                                modifier = Modifier.align(Alignment.End),
                                items = items,
                                vertical = true,
                                selectedIndex = selectedIndex,
                                onItemClick = onItemClick,
                            )
                        }
                    }
                }
            }
        }
    }*/
}

@Composable
fun TextButtonsComponents(
    items: List<String>,
    label: String,
    selectedIndex: Int?,
    onItemClick: (index: Int) -> Unit,
    compactButton: Boolean,
    modifier: Modifier = Modifier,
    buttonModifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = label,
        style = MaterialTheme.typography.titleLarge
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
    content: @Composable (inColumn: Boolean, compactButton: Boolean) -> Unit
) {
    MeasureViewWidth(
        modifier = modifier,
        viewToMeasure = listOf(
            {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleLarge,
                )
            },
            {
                SegmentedButton(
                    items = items
                )
            },
            {
                SegmentedButton(
                    items = items,
                    vertical = true
                )
            }
        )
    ) { maxWidth, measuredWidths ->
        val textWidth = measuredWidths[0]
        val buttonWidth = measuredWidths[1]
        val compactButtonWidth = measuredWidths[2]
        when {
            maxWidth >= textWidth + buttonWidth -> {
                content(
                    inColumn = false,
                    compactButton = false
                )
            }
            maxWidth >= buttonWidth -> {
                content(
                    inColumn = true,
                    compactButton = false
                )
            }
            maxWidth >= textWidth + compactButtonWidth -> {
                content(
                    inColumn = false,
                    compactButton = true
                )
            }
            else -> {
                content(
                    inColumn = true,
                    compactButton = true
                )
            }
        }
    }
}