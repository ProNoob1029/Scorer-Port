package com.dragos.scorerport.feature_editor.data.repository

import com.dragos.scorerport.feature_editor.data.data_source.FirebaseDatabase
import com.dragos.scorerport.feature_editor.domain.model.ListItemModel
import com.dragos.scorerport.feature_editor.domain.repository.ListRepository
import kotlinx.coroutines.flow.StateFlow

class ListRepositoryImpl(
    private val database: FirebaseDatabase
): ListRepository {
    override fun getList(): StateFlow<List<ListItemModel>> {
        return database.getList()
    }

    override fun changeLocation(location: String) {
        database.changeLocation(location)
    }
}