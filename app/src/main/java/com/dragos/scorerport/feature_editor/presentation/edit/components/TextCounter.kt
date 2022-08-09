package com.dragos.scorerport.feature_editor.presentation.edit.components

import android.view.HapticFeedbackConstants
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.dragos.scorerport.R
import com.dragos.scorerport.feature_editor.presentation.util.MeasureView

@Composable
fun TextCounter (
    modifier: Modifier = Modifier,
    counter: Int,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    plusEnabled: Boolean = true,
    minusEnabled: Boolean = true,
    onClickMinus: () -> Unit,
    onClickPlus: () -> Unit,
) {
    Measurements(
        modifier = modifier,
        counter = counter,
        text = text,
        textStyle = textStyle,
        plusEnabled = plusEnabled,
        minusEnabled = minusEnabled,
        onClickMinus = onClickMinus,
        onClickPlus = onClickPlus) { compact ->
        if (compact) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = text,
                    style = textStyle
                )
                Row(
                    modifier = Modifier.align(Alignment.End),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CounterAndButtons(
                        counter = counter,
                        textStyle = textStyle,
                        plusEnabled = plusEnabled,
                        minusEnabled = minusEnabled,
                        onClickMinus = onClickMinus,
                        onClickPlus = onClickPlus
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = text,
                    style = textStyle
                )
                CounterAndButtons(
                    counter = counter,
                    textStyle = textStyle,
                    plusEnabled = plusEnabled,
                    minusEnabled = minusEnabled,
                    onClickMinus = onClickMinus,
                    onClickPlus = onClickPlus
                )
            }
        }
    }
}

@Composable
internal fun Measurements (
    modifier: Modifier = Modifier,
    counter: Int,
    text: String,
    textStyle: TextStyle,
    plusEnabled: Boolean,
    minusEnabled: Boolean,
    onClickMinus: () -> Unit,
    onClickPlus: () -> Unit,
    content: @Composable (compact: Boolean) -> Unit
) {
    MeasureView(
        modifier = modifier,
        viewToMeasure =
        {
            Row {
                Text(
                    text = text,
                    style = textStyle
                )
                CounterAndButtons(
                    counter = counter,
                    textStyle = textStyle,
                    plusEnabled = plusEnabled,
                    minusEnabled = minusEnabled,
                    onClickMinus = onClickMinus,
                    onClickPlus = onClickPlus
                )
            }
        }
    ) { maxWidth, measuredWidth, _ ->
        val compact = maxWidth < measuredWidth
        content(compact)
    }
}

@Composable
internal fun CounterAndButtons (
    counter: Int,
    textStyle: TextStyle,
    plusEnabled: Boolean,
    minusEnabled: Boolean,
    onClickMinus: () -> Unit,
    onClickPlus: () -> Unit
) {
    Counter(
        modifier = Modifier.padding(horizontal = 16.dp),
        counter = counter,
        textStyle = textStyle
    )
    Buttons(
        onClickMinus = onClickMinus,
        onClickPlus = onClickPlus,
        plusEnabled = plusEnabled,
        minusEnabled = minusEnabled
    )
}

@Composable
internal fun Counter (
    modifier: Modifier = Modifier,
    counter: Int,
    textStyle: TextStyle,
) {
    Text (
        modifier = modifier,
        text = "$counter",
        style = textStyle.copy(fontFeatureSettings = "tnum")
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Buttons (
    modifier: Modifier = Modifier,
    plusEnabled: Boolean = true,
    minusEnabled: Boolean = true,
    onClickMinus: () -> Unit,
    onClickPlus: () -> Unit,
) {
    val minus = painterResource(id = R.drawable.ic_baseline_minus_24)
    val view = LocalView.current
    Row(modifier = modifier) {
        FilledIconButton(
            modifier = Modifier.size(50.dp),
            onClick = {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                onClickMinus()
            },
            enabled = minusEnabled
        ) {
            Icon(minus , contentDescription = "Minus")
        }
        Spacer(modifier = Modifier.width(8.dp))
        FilledIconButton(
            modifier = Modifier.size(50.dp),
            onClick = {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                onClickPlus()
            },
            enabled = plusEnabled
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }
}