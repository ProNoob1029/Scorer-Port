package com.dragos.scorerport.feature_editor.presentation.match_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.domain.util.MatchOrder
import com.dragos.scorerport.feature_editor.domain.util.OrderType
import com.dragos.scorerport.feature_editor.presentation.match_list.components.MatchCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    hapticContext: HapticFeedback,
) {
    val state = viewModel.state.value
    val listState = state.listState

    LazyColumn(state = listState) {
        itemsIndexed(
            items = state.matchList,
            key = { _, matchDisplay ->
                matchDisplay.key
            },
        ) { index , matchDisplay ->
            MatchCard(
                match = matchDisplay,
                index = index,
                onClick = {
                    viewModel.scorerApp.dynamicColorEnabled = true
                    hapticContext.performHapticFeedback(HapticFeedbackType.LongPress)
                    viewModel.onEvent(ListEvent.Order(MatchOrder.Date(OrderType.Descending)))
                          },
                onHold = {
                    viewModel.scorerApp.dynamicColorEnabled = false
                    hapticContext.performHapticFeedback(HapticFeedbackType.LongPress)
                    viewModel.onEvent(ListEvent.Order(MatchOrder.Date(OrderType.Ascending)))
                         },
                Modifier
                    .animateItemPlacement()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

