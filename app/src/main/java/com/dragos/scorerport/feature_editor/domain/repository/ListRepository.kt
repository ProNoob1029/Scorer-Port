package com.dragos.scorerport.feature_editor.domain.repository

import com.dragos.scorerport.feature_editor.domain.model.ListItemModel
import kotlinx.coroutines.flow.StateFlow

interface ListRepository {
    fun getList(): StateFlow<List<ListItemModel>>
    fun changeLocation(location: String)
}