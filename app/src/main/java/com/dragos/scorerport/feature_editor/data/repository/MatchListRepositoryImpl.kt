package com.dragos.scorerport.feature_editor.data.repository

import com.dragos.scorerport.feature_editor.data.data_source.FirebaseDatabase
import com.dragos.scorerport.feature_editor.domain.model.MatchDisplay
import com.dragos.scorerport.feature_editor.domain.repository.MatchListRepository
import kotlinx.coroutines.flow.StateFlow

class MatchListRepositoryImpl(
    private val database: FirebaseDatabase
): MatchListRepository {
    override fun getMatchList(): StateFlow<List<MatchDisplay>> {
        return database.getMatchList()
    }

    override fun changeLocation(location: String) {
        database.changeLocation(location)
    }
}