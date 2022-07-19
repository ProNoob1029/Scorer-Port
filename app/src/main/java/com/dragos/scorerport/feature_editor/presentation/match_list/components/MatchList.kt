package com.dragos.scorerport.feature_editor.presentation.match_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
    LazyColumn(
        //reverseLayout = true,
    ) {
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