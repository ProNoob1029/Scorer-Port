@file:OptIn(ExperimentalFoundationApi::class)

package com.dragos.scorerport.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.MatchDisplay

@Composable
fun MatchList(
    matchList: List<MatchDisplay>,
    onClick: () -> Unit,
    onHold: () -> Unit,
) {
    LazyColumn {
        itemsIndexed(
            items = matchList,
            key = { _, matchDisplay: MatchDisplay ->
                matchDisplay.key
            }
        ) { index: Int , matchDisplay: MatchDisplay ->
            MatchCard(
                match = matchDisplay,
                index = index,
                onClick = { onClick() },
                onHold = { onHold() },
                Modifier.animateItemPlacement(),
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

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
            .combinedClickable (
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
                text = (index + 1).toString().plus(". ").plus(match.name),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .weight(2f)
            )

            Column(
                horizontalAlignment = Alignment.End
            ){
                Text(
                    text = match.time,
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