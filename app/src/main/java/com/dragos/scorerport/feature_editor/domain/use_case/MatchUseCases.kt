package com.dragos.scorerport.feature_editor.domain.use_case

data class MatchUseCases(
    val getMatchList: GetMatchList,
    val getMatch: GetMatch,
    val saveMatch: SaveMatch,
    val getMatchFlow: GetMatchFlow
)
