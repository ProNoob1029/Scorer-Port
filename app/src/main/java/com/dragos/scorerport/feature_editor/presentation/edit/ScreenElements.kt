package com.dragos.scorerport.feature_editor.presentation.edit

import com.dragos.scorerport.feature_editor.domain.model.ItemEnum

interface Screen {
    val elements: List<ScreenElements>
}
interface ScreenElements
class Title(
    val title: String,
    val counter: Boolean = false,
    val largeTitle: Boolean = false
): ScreenElements
class TextField(
    val type: ItemEnum,
    val label: String
): ScreenElements
class AllianceButtons(
    val type: ItemEnum,
    val text1: String,
    val text2: String
): ScreenElements
class Switch(
    val type: ItemEnum,
    val label: String
): ScreenElements
class Counter(
    val type: ItemEnum,
    val label: String
): ScreenElements
class SegmentedButton(
    val type: ItemEnum,
    val label: String,
    val buttons: List<String>
): ScreenElements