package com.dragos.scorerport

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun MatchCard(
    match: MatchDisplay,
    index: Int,
    onClick: () -> Unit,
){
    Box(modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)) {
        Surface(
            shape = MaterialTheme.shapes.large,
            shadowElevation = 2.dp,
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .clip(shape = MaterialTheme.shapes.large)
                    .pointerInput(Unit){
                        detectTapGestures(
                            onLongPress = {onClick()}
                        )
                    },
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
    }
}