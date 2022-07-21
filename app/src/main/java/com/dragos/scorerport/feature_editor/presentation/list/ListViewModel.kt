package com.dragos.scorerport.feature_editor.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragos.scorerport.ScorerApp
import com.dragos.scorerport.feature_editor.domain.use_case.ListUseCases
import com.dragos.scorerport.feature_editor.domain.util.Order
import com.dragos.scorerport.feature_editor.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val listUseCases: ListUseCases,
    val scorerApp: ScorerApp
): ViewModel() {

    private val _state = mutableStateOf(ListState())
    val state: State<ListState> = _state

    private var job: Job? = null

    init {
        listUseCases.changeListLocation(state.value.listLocation)
        getListOrdered(Order.Date(OrderType.Descending))
    }

    fun onEvent(event: ListEvent) {
        when(event) {
            is ListEvent.Order -> {
                if (state.value.order::class == event.order::class &&
                    state.value.order.orderType::class == event.order.orderType::class
                ){return}
                getListOrdered(event.order)
            }
            is ListEvent.SwapLocation -> {
                if(state.value.listLocation == "test") {
                    listUseCases.changeListLocation("matchList")
                    _state.value = state.value.copy(
                        listLocation = "matchList"
                    )
                }
                else {
                    listUseCases.changeListLocation("test")
                    _state.value = state.value.copy(
                        listLocation = "test"
                    )
                }
            }
        }
    }

    private fun getListOrdered(order: Order) {
        job?.cancel()
        job = listUseCases.getList(order = order)
            .onEach { newList ->
                _state.value = state.value.copy(
                    list = newList,
                    order = order,
                )
            }
            .launchIn(viewModelScope)
    }
}