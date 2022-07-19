package com.dragos.scorerport.feature_editor.presentation.match_list.components

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.feature_editor.domain.model.MatchDisplay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MatchCard(
    match: MatchDisplay,
    index: Int,
    onClick: () -> Unit,
    onHold: () -> Unit,
    modifier: Modifier,
){
    Surface(
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clip(shape = MaterialTheme.shapes.large)
            .combinedClickable(
                onClick = { onClick() },
                onLongClick = { onHold() },
            ),
        color = MaterialTheme.colorScheme.secondaryContainer,
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
                text = match.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .weight(2f),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Column(
                horizontalAlignment = Alignment.End
            ){
                Text(
                    text = match.timeStamp.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = match.points.toString().plus(" points"),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}