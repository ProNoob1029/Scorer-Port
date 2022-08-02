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
    content: @Composable (compact: Boolean) -> Unit
) {
    BoxWithConstraints(modifier = modifier) {
        MeasureViewWidthInternal(viewToMeasure = viewToMeasure) { measuredWidth ->
            content(
                compact = measuredWidth >= maxWidth
            )
        }
    }
}

@Composable
fun MeasureViewWidthInDp (
    modifier: Modifier = Modifier,
    viewToMeasure: @Composable () -> Unit,
    content: @Composable (measuredWidth: Dp, maxWidth: Dp) -> Unit
) {
    BoxWithConstraints(modifier = modifier) {
        MeasureViewWidthInternal(viewToMeasure = viewToMeasure) { measuredWidth ->
            content(
                measuredWidth = measuredWidth,
                maxWidth = maxWidth
            )
        }
    }
}

@Composable
fun MeasureViewWidthInternal (
    viewToMeasure: @Composable () -> Unit,
    content: @Composable (measuredWidth: Dp) -> Unit
) {
    SubcomposeLayout { constraints ->
        val measuredWidth = subcompose("viewToMeasure", viewToMeasure)[0]
            .measure(Constraints()).width.toDp()

        val contentPlaceable = subcompose("content") {
            content(measuredWidth)
        }[0].measure(constraints)
        layout(contentPlaceable.width, contentPlaceable.height) {
            contentPlaceable.place(0, 0)
        }
    }
}