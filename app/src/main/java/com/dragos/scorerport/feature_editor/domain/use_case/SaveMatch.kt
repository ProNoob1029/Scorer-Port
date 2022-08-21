package com.dragos.scorerport.feature_editor.domain.use_case

import com.dragos.scorerport.feature_editor.domain.model.Match
import com.dragos.scorerport.feature_editor.domain.repository.Repository

class SaveMatch(
    private val repository: Repository
) {
    suspend operator fun invoke(match: Match) {
        repository.insertMatch(match)
    }
}