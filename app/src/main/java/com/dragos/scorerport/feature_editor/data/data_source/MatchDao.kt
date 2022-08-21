package com.dragos.scorerport.feature_editor.data.data_source

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dragos.scorerport.feature_editor.domain.model.Match
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface MatchDao {

    @Query("SELECT * FROM `match`")
    fun getMatches(): Flow<List<Match>>

    @Query("SELECT * FROM `match` WHERE `key` = :key")
    suspend fun getMatchByKey(key: String): Match?

    @Query("SELECT * FROM `match` WHERE `key` = :key")
    fun getMatchFlowByKey(key: String): Flow<Match?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatch(match: Match)

    @Delete
    suspend fun deleteMatch(match: Match)
}