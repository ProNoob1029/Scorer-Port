package com.dragos.scorerport.feature_editor.data.repository

import com.dragos.scorerport.feature_editor.domain.model.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestRepositoryImpl {
    fun getList(): Flow<List<Match>> {
        return flow {
            emit(
                listOf(
                    Match(
                        title = "uwu",
                        createStamp = 100,
                        totalPoints = 69,
                        key = "a"
                    ),
                    Match(
                        title = "owo",
                        createStamp = 50,
                        totalPoints = 420,
                        key = "b"
                    )
                )
            )
        }
    }

    fun changeLocation(location: String) {
        //TODO("Not yet implemented")
    }

}