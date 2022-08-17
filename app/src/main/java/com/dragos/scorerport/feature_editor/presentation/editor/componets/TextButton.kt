package com.dragos.scorerport.feature_editor.presentation.editor.componets

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.presentation.editor.EditorViewModel
import com.dragos.scorerport.feature_editor.presentation.util.MeasureView
import com.dragos.scorerport.feature_editor.presentation.util.SegmentedButton
import com.dragos.scorerport.impl.freightfrenzy.MatchEnum

@Composable
fun TextButton (
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(top = 8.dp, start = 16.dp, end = 16.dp),
    label: String,
    buttons: List<String>,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    type: MatchEnum.Ints,
    viewModel: EditorViewModel = hiltViewModel()
) {
    val activeIndex by rememberSaveable { viewModel.get(type) }

    val visible by rememberSaveable { viewModel.getVisibility(type) }

    val specialColor = rememberSaveable { viewModel.getSpecialColor(type) }

    val color = if (specialColor) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary

    if (visible) {
        Measure(
            modifier = modifier.padding(paddingValues),
            label = label,
            buttons = buttons,
            textStyle = textStyle
        ) { modifier1, modifier2 ->
            Text(modifier = modifier1, text = label, style = textStyle)
            SegmentedButton(
                modifier = modifier2,
                items = buttons,
                selectedIndex = activeIndex.convert(),
                color = color,
                onItemClick = {
                    viewModel.set(
                        type = type,
                        value = if (it + 1 == activeIndex) 0 else it + 1
                    )
                }
            )
        }
    }
}

@Composable
internal fun Measure (
    modifier: Modifier,
    label: String,
    buttons: List<String>,
    textStyle: TextStyle,
    content: @Composable (modifier1: Modifier, modifier2: Modifier) -> Unit
) {
    MeasureView(
        modifier = modifier,
        viewToMeasure = {
            Row {
                Text(modifier = Modifier.width(IntrinsicSize.Min), text = label, style = textStyle)
                SegmentedButton(items = buttons)
            }
        }
    ) { maxWidth, measuredWidth, _ ->
        val vertical = maxWidth < measuredWidth

        if (vertical) {
            Column(modifier = Modifier.fillMaxWidth()) {
                content(Modifier, Modifier.align(Alignment.End))
            }
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                content(Modifier.weight(1f), Modifier)
            }
        }
    }
}

private fun Int.convert(): Int? = if (this == 0) null else this - 1