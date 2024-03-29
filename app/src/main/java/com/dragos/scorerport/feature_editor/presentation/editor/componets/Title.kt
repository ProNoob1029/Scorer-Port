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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.domain.model.MatchEnum
import com.dragos.scorerport.feature_editor.presentation.editor.EditorViewModel
import com.dragos.scorerport.feature_editor.presentation.util.MeasureView

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
    val counter by remember { viewModel.state.get(type) }

    Surface (
        modifier = modifier
            .padding(paddingValues)
            .fillMaxWidth(),
        color = surfaceColor
    ) {
        MeasureView(
            modifier = Modifier.padding(horizontal = 16.dp),
            viewToMeasure = {
                Text(
                    text = "$title$counter",
                    style = textStyle
                )
            }
        ) { maxWidth, measuredWidth, _ ->
            val compact = maxWidth < measuredWidth
            if (compact) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "$title$counter",
                    style = textStyle.copy(fontFeatureSettings = "tnum"),
                    textAlign = TextAlign.Center
                )
            } else {
                Row(
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
    }
}