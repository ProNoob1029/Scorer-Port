package com.dragos.scorerport.feature_editor.presentation.list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.feature_editor.domain.model.ListItemModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemCard(
    item: ListItemModel,
    index: Int,
    onClick: () -> Unit,
    onHold: () -> Unit,
    modifier: Modifier,
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
){
    val hapticContext = LocalHapticFeedback.current
    Surface(
        modifier = modifier
            .fillMaxWidth(1f)
            .clip(shape = MaterialTheme.shapes.large)
            .combinedClickable(
                onClick = {
                    hapticContext.performHapticFeedback(HapticFeedbackType.LongPress)
                    onClick()
                          },
                onLongClick = {
                    hapticContext.performHapticFeedback(HapticFeedbackType.LongPress)
                    onHold()
                              },
            ),
        color = containerColor,
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = (index + 1).toString().plus(". "),
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .weight(2f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )

            Column(
                horizontalAlignment = Alignment.End
            ){
                Text(
                    text = item.timeStamp.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = item.points.toString().plus(" points"),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}