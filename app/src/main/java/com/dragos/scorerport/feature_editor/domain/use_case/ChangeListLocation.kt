package com.dragos.scorerport.feature_editor.domain.use_case

import com.dragos.scorerport.feature_editor.domain.repository.ListRepository

class ChangeListLocation(
    private val repository: ListRepository
) {
    operator fun invoke(
        location: String
    ) {
        repository.changeLocation(location)
    }
}