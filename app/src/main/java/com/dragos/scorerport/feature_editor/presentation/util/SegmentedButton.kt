package com.dragos.scorerport.feature_editor.presentation.util

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * items : list of items to be render
 * defaultSelectedItemIndex : to highlight item by default (Optional)
 * useFixedWidth : set true if you want to set fix width to item (Optional)
 * itemWidth : Provide item width if useFixedWidth is set to true (Optional)
 * cornerRadius : To make control as rounded (Optional)
 * color : Set color to control (Optional)
 * onItemSelection : Get selected item index
 */
@ExperimentalMaterial3Api
@Composable
fun SegmentedButton(
    items: List<String>,
    selectedIndex: Int?,
    onItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    enabled: Boolean = true,
) {

    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .width(IntrinsicSize.Max)
    ) {
        items.forEachIndexed { index, item ->
            val selected = selectedIndex == index
            val surfaceColor by animateColorAsState(
                if (selected) color else Color.Transparent,
            )
            val contentColor by animateColorAsState(
                if (selected) contentColorFor(backgroundColor = color) else MaterialTheme.colorScheme.onSurface,
            )
            LocalDensity.current
            OutlinedButton(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = when (index) {
                    0 -> RoundedCornerShape(
                        topStartPercent = 50,
                        topEndPercent = 0,
                        bottomStartPercent = 50,
                        bottomEndPercent = 0
                    )
                    items.size - 1 -> RoundedCornerShape(
                        topStartPercent = 0,
                        topEndPercent = 50,
                        bottomStartPercent = 0,
                        bottomEndPercent = 50
                    )
                    else -> RoundedCornerShape(
                        topStartPercent = 0,
                        topEndPercent = 0,
                        bottomStartPercent = 0,
                        bottomEndPercent = 0
                    )
                },
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.outline
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = surfaceColor,
                    contentColor = contentColor
                ),
                onClick = {onItemClick(index)},
                contentPadding = PaddingValues(
                    horizontal = 8.dp
                ),
                enabled = enabled,
            ) {
                if(selected)
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = "checkIcon",
                    )
                
                Text(
                    text = item,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .wrapContentHeight()
                )
            }
        }
    }
}