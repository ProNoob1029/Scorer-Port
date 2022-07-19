package com.dragos.scorerport.feature_editor.domain.use_case

import com.dragos.scorerport.feature_editor.domain.repository.MatchListRepository

class ChangeLocation(
    private val repository: MatchListRepository
) {
    operator fun invoke(
        location: String
    ) {
        repository.changeLocation(location)
    }
}