package com.dragos.scorerport.feature_editor.presentation.list.components

import android.view.HapticFeedbackConstants
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
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.feature_editor.domain.model.Match

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemCard(
    item: Match,
    index: Int,
    onClick: () -> Unit,
    onHold: () -> Unit,
    modifier: Modifier,
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    titleStyle: TextStyle = MaterialTheme.typography.titleLarge,
    infoStyle: TextStyle = MaterialTheme.typography.titleSmall
){
    val view = LocalView.current

    val newIndex = index + 1
    val points = item.totalPoints
    val timeStamp = item.createStamp

    Surface(
        modifier = modifier
            .fillMaxWidth(1f)
            .clip(shape = MaterialTheme.shapes.large)
            .combinedClickable(
                onClick = {
                    view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                    onClick()
                          },
                onLongClick = {
                    view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
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
                text = "$newIndex. ",
                style = titleStyle
            )
            Text(
                text = item.title,
                modifier = Modifier
                    .weight(2f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = titleStyle
            )

            Column(
                horizontalAlignment = Alignment.End
            ){
                Text(
                    text = "$timeStamp",
                    style = infoStyle
                )
                Text(
                    text = "$points points",
                    style = infoStyle
                )
            }
        }
    }
}