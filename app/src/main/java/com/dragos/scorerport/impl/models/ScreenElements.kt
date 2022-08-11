package com.dragos.scorerport.impl.models

sealed class ScreenElements {
    class Title(
        val type: ItemEnum.Ints,
        val title: String,
        val counter: Boolean = false,
        val largeTitle: Boolean = false
    ): ScreenElements()
    class TextField(
        val type: ItemEnum.Strings,
        val label: String
    ): ScreenElements()
    class AllianceButtons(
        val type: ItemEnum.Ints,
        val text1: String,
        val text2: String
    ): ScreenElements()
    class Switch(
        val type: ItemEnum.Booleans,
        val label: String
    ): ScreenElements()
    class Counter(
        val type: ItemEnum.Counters,
        val label: String
    ): ScreenElements()
    class SegmentedButton(
        val type: ItemEnum.Ints,
        val label: String,
        val buttons: List<String>
    ): ScreenElements()
}