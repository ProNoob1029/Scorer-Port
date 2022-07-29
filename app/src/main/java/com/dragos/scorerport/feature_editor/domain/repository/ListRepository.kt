package com.dragos.scorerport.feature_editor.domain.repository

import com.dragos.scorerport.feature_editor.domain.model.ListItemModel
import kotlinx.coroutines.flow.Flow

interface ListRepository {
    fun getList(): Flow<List<ListItemModel>>
    fun changeLocation(location: String)
}