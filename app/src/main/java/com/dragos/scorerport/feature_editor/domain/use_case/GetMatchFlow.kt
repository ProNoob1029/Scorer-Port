package com.dragos.scorerport.feature_editor.domain.use_case

import com.dragos.scorerport.feature_editor.domain.model.Match
import com.dragos.scorerport.feature_editor.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetMatchFlow(
    private val repository: Repository
) {
    operator fun invoke(key: String): Flow<Match?> {
        return repository.getMatchFlowByKey(key)
    }
}