package com.dragos.scorerport.feature_editor.presentation.edit

import com.dragos.scorerport.feature_editor.domain.model.Match

val screen = listOf(
    ScreenElem.Title("New Match", largeTitle = true),
    ScreenElem.TextField(Match.Title, "Title"),
    ScreenElem.AllianceButtons(Match.Alliance, "Red Alliance", "Blue Alliance"),
    ScreenElem.Title("Autonomous: ", true),
    ScreenElem.Switch(Match.AutoDuck, "Duck delivery: "),
    ScreenElem.Counter(Match.AutoStorage, "Freight in storage: "),
    ScreenElem.Counter(Match.AutoHub, "Freight in hub: "),
    ScreenElem.SegmentedButton(Match.AutoParked, "Parked in: ", listOf("Storage", "Warehouse"))
)

sealed class ScreenElem {
    class Title(
        val title: String,
        val counter: Boolean = false,
        val largeTitle: Boolean = false
    ): ScreenElem()
    class TextField(
        val type: Match,
        val label: String
    ): ScreenElem()
    class AllianceButtons(
        val type: Match,
        val text1: String,
        val text2: String
    ): ScreenElem()
    class Switch(
        val type: Match,
        val label: String
    ): ScreenElem()
    class Counter(
        val type: Match,
        val label: String
    ): ScreenElem()
    class SegmentedButton(
        val type: Match,
        val label: String,
        val buttons: List<String>
    ): ScreenElem()
}