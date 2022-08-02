package com.dragos.scorerport.feature_editor.presentation.edit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dragos.scorerport.feature_editor.presentation.util.MeasureViewWidthInDp
import com.dragos.scorerport.feature_editor.presentation.util.SegmentedButton

@Composable
fun TextButtons (
    modifier: Modifier = Modifier,
    items: List<String>,
    label: String,
    selectedIndex: Int?,
    onItemClick: (index: Int) -> Unit,
) {

    MeasureViewWidthInDp(
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
                            compact = true,
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
                                compact = true,
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
                                compact = true,
                                selectedIndex = selectedIndex,
                                onItemClick = onItemClick,
                            )
                        }
                    }
                }
            }
        }
    }
}