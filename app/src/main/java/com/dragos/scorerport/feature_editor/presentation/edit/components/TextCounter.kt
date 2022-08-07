package com.dragos.scorerport.feature_editor.presentation.edit.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun TextCounter (
    modifier: Modifier = Modifier,
    counter: Int,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge
) {
    Counter(
        modifier = modifier,
        counter = counter,
        textStyle = textStyle
    )
}

@Composable
internal fun Counter (
    modifier: Modifier = Modifier,
    counter: Int,
    textStyle: TextStyle
) {
    Text (
        modifier = modifier,
        text = "$counter",
        style = textStyle.copy(fontFeatureSettings = "tnum")
    )
}