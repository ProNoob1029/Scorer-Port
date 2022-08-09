package com.dragos.scorerport.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

data class Padding(
    val inRow: PaddingValues = PaddingValues(top = 8.dp, start = 16.dp, end = 16.dp)
)

val LocalPadding = compositionLocalOf { Padding() }

val MaterialTheme.padding: Padding
    @Composable
    @ReadOnlyComposable
    get() = LocalPadding.current