package com.dragos.scorerport.feature_editor.domain.use_case

import com.dragos.scorerport.feature_editor.domain.model.Match
import com.dragos.scorerport.feature_editor.domain.repository.Repository

class GetMatch (
    private val repository: Repository
) {
    suspend operator fun invoke(
        key: String
    ): Match? {
        return repository.getMatchByKey(key)
    }
}