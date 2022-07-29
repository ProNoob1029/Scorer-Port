package com.dragos.scorerport.feature_editor.data.repository

import com.dragos.scorerport.feature_editor.domain.model.ListItemModel
import com.dragos.scorerport.feature_editor.domain.repository.ListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestRepositoryImpl: ListRepository {
    override fun getList(): Flow<List<ListItemModel>> {
        return flow {
            emit(
                listOf(
                    ListItemModel(
                        title = "uwu",
                        timeStamp = 100,
                        points = 69,
                        key = "a"
                    ),
                    ListItemModel(
                        title = "owo",
                        timeStamp = 50,
                        points = 420,
                        key = "b"
                    )
                )
            )
        }
    }

    override fun changeLocation(location: String) {
        //TODO("Not yet implemented")
    }

}