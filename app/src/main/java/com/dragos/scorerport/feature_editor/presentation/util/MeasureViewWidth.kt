package com.dragos.scorerport.feature_editor.presentation.util

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp

@Composable
fun MeasureViewWidth (
    modifier: Modifier = Modifier,
    viewToMeasure: @Composable () -> Unit,
    content: @Composable (maxWidth: Dp, measuredWidth: Dp) -> Unit
) {
    BoxWithConstraints(modifier = modifier) {
        MeasureViewWidthInternal(viewToMeasure = listOf(viewToMeasure)) { measuredWidth ->
            content(
                measuredWidth = measuredWidth[0],
                maxWidth = maxWidth
            )
        }
    }
}

@Composable
fun MeasureViewWidth (
    modifier: Modifier = Modifier,
    viewToMeasure: List<@Composable () -> Unit>,
    content: @Composable (maxWidth: Dp, measuredWidths: List<Dp>) -> Unit
) {
    BoxWithConstraints(modifier = modifier) {
        MeasureViewWidthInternal(
            viewToMeasure = viewToMeasure
        ) { newMeasuredWidths ->
            content(
                maxWidth = maxWidth,
                measuredWidths = newMeasuredWidths
            )
        }
    }
}

@Composable
fun MeasureViewWidthInternal (
    viewToMeasure: List<@Composable () -> Unit>,
    content: @Composable (measuredWidths: List<Dp>) -> Unit
) {
    val measuredWidths = mutableListOf<Dp>()
    SubcomposeLayout { constraints ->
        viewToMeasure.forEachIndexed { index, viewToMeasure ->
            measuredWidths.add(subcompose("viewToMeasure$index", viewToMeasure)[0]
                .measure(Constraints()).width.toDp())
        }
        val contentPlaceable = subcompose("content") {
            content(measuredWidths)
        }[0].measure(constraints)
        layout(contentPlaceable.width, contentPlaceable.height) {
            contentPlaceable.place(0, 0)
        }
    }
}