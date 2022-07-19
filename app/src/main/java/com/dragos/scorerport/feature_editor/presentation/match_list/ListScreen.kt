package com.dragos.scorerport.feature_editor.presentation.match_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.domain.util.MatchOrder
import com.dragos.scorerport.feature_editor.domain.util.OrderType
import com.dragos.scorerport.feature_editor.presentation.match_list.components.MatchList

@Composable
fun MainActivityCompose(
    viewModel: ListViewModel = hiltViewModel(),
    hapticContext: HapticFeedback,
) {
    val state = viewModel.state.value
    MatchList(
        state.matchList,
        onClick = {
            viewModel.scorerApp.dynamicColorEnabled = true
            hapticContext.performHapticFeedback(HapticFeedbackType.LongPress)
            //viewModel.getMatchDisplayList("test", MatchOrder.Date(OrderType.Descending))
            viewModel.onEvent(ListEvent.Order(MatchOrder.Date(OrderType.Descending)))
        },
        onHold = {
            viewModel.scorerApp.dynamicColorEnabled = false
            hapticContext.performHapticFeedback(HapticFeedbackType.LongPress)
            //viewModel.getMatchDisplayList("test", MatchOrder.Date(OrderType.Descending))
            viewModel.onEvent(ListEvent.Order(MatchOrder.Date(OrderType.Ascending)))
        },
    )
}

