package com.dragos.scorerport.feature_editor.presentation.edit.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.feature_editor.presentation.util.MeasureView

@Composable
fun TitleCard (
    modifier: Modifier = Modifier,
    title: String,
    points: Int? = null,
    surfaceColor: Color = MaterialTheme.colorScheme.primaryContainer,
    titleStyle: TextStyle = MaterialTheme.typography.headlineMedium,
) {
    val hasPoints = points != null

    Surface(
        modifier = modifier,
        color = surfaceColor
    ) {
        if(hasPoints) {
            MeasureView(
                modifier = Modifier.padding(horizontal = 16.dp),
                viewToMeasure = {
                    Text(
                        text = "$title$points points",
                        style = titleStyle.copy(fontFeatureSettings = "tnum"),
                    )
                }
            ) { maxWidth, measuredWidth, _ ->

                val compact = maxWidth < measuredWidth

                if(compact) {
                    Text(
                        text = "$title$points points",
                        style = titleStyle.copy(fontFeatureSettings = "tnum"),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                } else {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = title,
                            style = titleStyle,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "$points points",
                            style = titleStyle.copy(fontFeatureSettings = "tnum"),
                            textAlign = TextAlign.End
                        )
                    }
                }
            }
        } else {
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = titleStyle,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}