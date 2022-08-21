package com.dragos.scorerport.feature_editor.data.repository

import com.dragos.scorerport.feature_editor.data.data_source.MatchDao
import com.dragos.scorerport.feature_editor.domain.model.Match
import com.dragos.scorerport.feature_editor.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val dao: MatchDao
): Repository {

    override fun getMatches(): Flow<List<Match>> {
        return dao.getMatches()
    }

    override fun getMatchFlowByKey(key: String): Flow<Match?> {
        return dao.getMatchFlowByKey(key)
    }

    override suspend fun getMatchByKey(key: String): Match? {
        return dao.getMatchByKey(key)
    }

    override suspend fun insertMatch(match: Match) {
        return dao.insertMatch(match)
    }

    override suspend fun deleteMatch(match: Match) {
        return dao.deleteMatch(match)
    }
}