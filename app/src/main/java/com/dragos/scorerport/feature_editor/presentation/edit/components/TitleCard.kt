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
import com.dragos.scorerport.feature_editor.presentation.util.MeasureViewWidth

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
            MeasureViewWidth(
                modifier = Modifier.padding(horizontal = 16.dp),
                viewToMeasure = {
                    Text(text = "$title$points points", style = titleStyle)
                }
            ) { maxWidth, measuredWidth ->

                val compact = maxWidth < measuredWidth

                if(compact) {
                    Text(
                        text = "$title$points points",
                        modifier = Modifier.fillMaxWidth(),
                        style = titleStyle,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = title,
                            textAlign = TextAlign.Start,
                            style = titleStyle,
                            modifier = Modifier.weight(1f),
                        )
                        Text(
                            text = "$points points",
                            textAlign = TextAlign.End,
                            style = titleStyle,
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