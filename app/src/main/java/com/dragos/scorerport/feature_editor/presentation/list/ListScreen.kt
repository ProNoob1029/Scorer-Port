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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.domain.util.Order
import com.dragos.scorerport.feature_editor.domain.util.OrderType
import com.dragos.scorerport.feature_editor.presentation.list.components.ItemCard

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                /*TODO*/
                },
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add match")
            }
        },
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
        ) {
            item {
                Spacer(modifier = Modifier.height(4.dp))
            }

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
                        //viewModel.onEvent(ListEvent.Order(Order.Date(OrderType.Descending)))
                        //viewModel.onEvent(ListEvent.SwapLocation)
                    },
                    onHold = {
                        //viewModel.scorerApp.dynamicColorEnabled = false
                        //viewModel.onEvent(ListEvent.Order(Order.Date(OrderType.Ascending)))
                        //viewModel.onEvent(ListEvent.SwapLocation)
                    },
                    modifier = Modifier
                        .animateItemPlacement()
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                )
            }
            item {
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

