package com.dragos.scorerport.feature_editor.presentation.match_list

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragos.scorerport.ScorerApp
import com.dragos.scorerport.feature_editor.domain.use_case.MatchListUseCases
import com.dragos.scorerport.feature_editor.domain.util.MatchOrder
import com.dragos.scorerport.feature_editor.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val matchListUseCases: MatchListUseCases,
    val scorerApp: ScorerApp
): ViewModel() {

    private val _state = mutableStateOf(MatchListState())
    val state: State<MatchListState> = _state

    private var getMatchJob: Job? = null

    init {
        matchListUseCases.changeLocation("matchList")
        getMatchDisplayList(MatchOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: ListEvent) {
        when(event) {
            is ListEvent.Order -> {
                if (state.value.matchOrder::class == event.matchOrder::class &&
                    state.value.matchOrder.orderType::class == event.matchOrder.orderType::class
                ){return}
                getMatchDisplayList(event.matchOrder, true)
            }
        }
    }

    private fun getMatchDisplayList(matchOrder: MatchOrder, reorder: Boolean = false) {
        var doReorder = reorder
        getMatchJob?.cancel()
        getMatchJob = matchListUseCases.getMatchList(matchOrder = matchOrder)
            .onEach { newMatchList ->
                if (doReorder) {
                    _state.value = state.value.copy(
                        matchList = newMatchList,
                        matchOrder = matchOrder,
                        listState = LazyListState(0, 0)
                    )
                    doReorder = false
                } else {
                    _state.value = state.value.copy(
                        matchList = newMatchList,
                        matchOrder = matchOrder,
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}