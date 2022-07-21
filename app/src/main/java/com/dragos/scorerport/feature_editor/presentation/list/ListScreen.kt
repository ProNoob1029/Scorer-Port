package com.dragos.scorerport.feature_editor.presentation.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.domain.util.Order
import com.dragos.scorerport.feature_editor.domain.util.OrderType
import com.dragos.scorerport.feature_editor.presentation.list.components.ItemCard

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    hapticContext: HapticFeedback,
) {
    val state = viewModel.state.value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                /*TODO*/
                },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add match")
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
        ) {
            itemsIndexed(
                items = state.list,
                key = { _, item ->
                    when(state.order.orderType) {
                        is OrderType.Ascending -> {
                            when(state.order) {
                                is Order.Name -> item.key.plus(1)
                                is Order.Date -> item.key.plus(2)
                                is Order.Points -> item.key.plus(3)
                            }
                        }
                        is OrderType.Descending -> {
                            when(state.order) {
                                is Order.Name -> item.key.plus(4)
                                is Order.Date -> item.key.plus(5)
                                is Order.Points -> item.key.plus(6)
                            }
                        }
                    }
                },
            ) { index , item ->
                ItemCard(
                    item = item,
                    index = index,
                    onClick = {
                        //viewModel.scorerApp.dynamicColorEnabled = true
                        hapticContext.performHapticFeedback(HapticFeedbackType.LongPress)
                        //viewModel.onEvent(ListEvent.Order(Order.Date(OrderType.Descending)))
                        viewModel.onEvent(ListEvent.SwapLocation)
                    },
                    onHold = {
                        //viewModel.scorerApp.dynamicColorEnabled = false
                        hapticContext.performHapticFeedback(HapticFeedbackType.LongPress)
                        //viewModel.onEvent(ListEvent.Order(Order.Date(OrderType.Ascending)))
                        viewModel.onEvent(ListEvent.SwapLocation)
                    },
                    modifier = Modifier
                        .animateItemPlacement()
                        .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
