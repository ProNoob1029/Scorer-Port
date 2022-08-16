package com.dragos.scorerport.feature_editor.presentation.editor.componets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.presentation.editor.EditorViewModel
import com.dragos.scorerport.impl.freightfrenzy.MatchEnum

@Composable
fun Title (
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(top = 8.dp),
    title: String,
    type: MatchEnum.Ints,
    textStyle: TextStyle = MaterialTheme.typography.headlineMedium,
    viewModel: EditorViewModel = hiltViewModel(),
    surfaceColor: Color = MaterialTheme.colorScheme.primaryContainer,
) {
    val counter by rememberSaveable { viewModel.get(type) }

    Surface (
        modifier = modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        color = surfaceColor
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                style = textStyle,
                textAlign = TextAlign.Start
            )
            Text(
                text = "$counter",
                style = textStyle.copy(fontFeatureSettings = "tnum"),
                textAlign = TextAlign.End
            )
        }
    }
}