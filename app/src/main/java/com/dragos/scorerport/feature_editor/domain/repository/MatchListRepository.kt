package com.dragos.scorerport.feature_editor.domain.repository

import com.dragos.scorerport.feature_editor.domain.model.MatchDisplay
import kotlinx.coroutines.flow.StateFlow

interface MatchListRepository {
    fun getMatchList(): StateFlow<List<MatchDisplay>>
    fun changeLocation(location: String)
}