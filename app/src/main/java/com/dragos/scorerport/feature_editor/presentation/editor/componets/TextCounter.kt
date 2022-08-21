package com.dragos.scorerport.feature_editor.presentation.editor.componets

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.R
import com.dragos.scorerport.feature_editor.domain.model.MatchEnum
import com.dragos.scorerport.feature_editor.presentation.editor.EditorViewModel
import com.dragos.scorerport.feature_editor.presentation.util.MeasureView

@Composable
fun TextCounter (
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(top = 8.dp, start = 16.dp, end = 16.dp),
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    type: MatchEnum.Counters,
    viewModel: EditorViewModel = hiltViewModel()
) {
    val counter by remember { viewModel.state.get(type) }

    Measure(
        modifier = modifier.padding(paddingValues),
        counter = counter,
        textStyle = textStyle,
        text = text
    ) { modifier1, modifier2 ->
        Text(modifier = modifier1, text = text, style = textStyle)
        Counter(
            modifier = modifier2,
            counter = counter,
            textStyle = textStyle,
            onClick = { viewModel.state.set(type, it) }
        )
    }
}

@Composable
internal fun Measure (
    modifier: Modifier,
    counter: Int,
    textStyle: TextStyle,
    text: String,
    content: @Composable (modifier1: Modifier, modifier2: Modifier) -> Unit
) {
    MeasureView(
        modifier = modifier,
        viewToMeasure = {
            Row {
                Text(modifier = Modifier.width(IntrinsicSize.Min), text = text, style = textStyle)
                Counter(
                    counter = counter,
                    textStyle = textStyle,
                    onClick = {})
            }
        },
    ) { maxWidth, measuredWidth, _ ->
        val vertical = maxWidth < measuredWidth

        if (vertical) {
            Column(Modifier.fillMaxWidth()) {
                content(Modifier, Modifier.align(Alignment.End))
            }
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                content(Modifier.weight(1f), Modifier)
            }
        }
    }
}

@Composable
internal fun Counter (
    modifier: Modifier = Modifier,
    counter: Int,
    textStyle: TextStyle,
    onClick: (add: Int) -> Unit
) {
    val minus = painterResource(id = R.drawable.ic_baseline_minus_24)
    val view = LocalView.current
    Row(
        modifier = modifier
            .width(200.dp)
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text (
            modifier = modifier
                .weight(1f)
                .fillMaxHeight()
                .wrapContentHeight(),
            text = "$counter",
            style = textStyle.copy(fontFeatureSettings = "tnum"),
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            FilledIconButton(
                modifier = Modifier.size(50.dp),
                onClick = {
                    view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                    onClick(-1)
                },
                enabled = counter > 0
            ) {
                Icon(minus , contentDescription = "Minus")
            }
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            FilledIconButton(
                modifier = Modifier.size(50.dp),
                onClick = {
                    view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                    onClick(1)
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }
}