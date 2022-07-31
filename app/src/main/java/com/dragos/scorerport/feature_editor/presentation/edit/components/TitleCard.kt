package com.dragos.scorerport.feature_editor.presentation.edit.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.dragos.scorerport.feature_editor.presentation.util.MeasureViewWidth

@Composable
fun TitleCard (
    modifier: Modifier = Modifier,
    title: String,
    points: Int? = null,
    surfaceColor: Color = MaterialTheme.colorScheme.primaryContainer,
    titleStyle: TextStyle = MaterialTheme.typography.headlineLarge,
) {
    val hasPoints = points != null

    Surface(color = surfaceColor) {
        if(hasPoints) {
            BoxWithConstraints(
                modifier = modifier
            ) {
                MeasureViewWidth(
                    viewToMeasure = {
                        Text(text = "$title$points points", style = titleStyle)
                    }
                ) { measuredWidth ->
                    if(measuredWidth > maxWidth) {
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
            }
        } else {
            Box(modifier = modifier) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = titleStyle,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }



    /*Surface(
        color = surfaceColor,
        modifier = modifier,
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = title,
                textAlign =
                if (hasPoints)
                    TextAlign.Start
                else
                    TextAlign.Center,
                style = titleStyle,
                modifier = Modifier
                *//*if (hasPoints)
                    Modifier
                else
                    Modifier.weight(1f)*//*,
            )

            if(hasPoints)

                //Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "$points points",
                    textAlign = TextAlign.End,
                    style = titleStyle,
                )
        }
    }*/
}