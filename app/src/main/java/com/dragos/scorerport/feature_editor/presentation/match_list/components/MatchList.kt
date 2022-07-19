package com.dragos.scorerport.feature_editor.presentation.match_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.feature_editor.domain.model.MatchDisplay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MatchList(
    matchList: List<MatchDisplay>,
    onClick: () -> Unit,
    onHold: () -> Unit,
) {
    LazyColumn {
        itemsIndexed(
            items = matchList,
            key = { _, matchDisplay ->
                matchDisplay.key
            }
        ) { index , matchDisplay ->
            MatchCard(
                match = matchDisplay,
                index = index,
                onClick = { onClick() },
                onHold = { onHold() },
                Modifier.animateItemPlacement()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}