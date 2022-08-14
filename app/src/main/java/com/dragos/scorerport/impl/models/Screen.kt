package com.dragos.scorerport.impl.models

interface Screen {
    val elements: List<ScreenElements>
    val title: String
    val title1: String
    val title2: String
    val titleType: ItemEnum.Booleans
}