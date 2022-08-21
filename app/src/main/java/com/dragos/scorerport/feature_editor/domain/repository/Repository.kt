package com.dragos.scorerport.feature_editor.domain.repository

import com.dragos.scorerport.feature_editor.domain.model.Match
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getMatches(): Flow<List<Match>>

    fun getMatchFlowByKey(key: String): Flow<Match?>

    suspend fun getMatchByKey(key: String): Match?

    suspend fun insertMatch(match: Match)

    suspend fun deleteMatch(match: Match)
}