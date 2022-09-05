package com.dragos.scorerport.feature_editor.presentation.editor.componets

import android.view.HapticFeedbackConstants
import androidx.compose.animation.*
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
    val enabled by remember { viewModel.editEnabled }



    Measure(
        modifier = modifier.padding(paddingValues),
        counter = counter,
        textStyle = textStyle,
        text = text,
        enabled = enabled
    ) { modifier1, modifier2 ->
        Text(modifier = modifier1, text = text, style = textStyle)
        Counter(
            modifier = modifier2,
            counter = counter,
            textStyle = textStyle,
            onClick = { viewModel.state.set(type, it) },
            enabled = enabled,
        )
    }
}

@Composable
private fun Measure (
    modifier: Modifier,
    counter: Int,
    textStyle: TextStyle,
    text: String,
    enabled: Boolean,
    content: @Composable (modifier1: Modifier, modifier2: Modifier) -> Unit,
) {
    MeasureView(
        modifier = modifier,
        viewToMeasure = {
            Row {
                Text(modifier = Modifier.width(IntrinsicSize.Min), text = text, style = textStyle)
                Counter(
                    counter = counter,
                    textStyle = textStyle,
                    onClick = {},
                    enabled = enabled
                )
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun Counter (
    modifier: Modifier = Modifier,
    counter: Int,
    textStyle: TextStyle,
    onClick: (add: Int) -> Unit,
    enabled: Boolean,
) {
    val minus = painterResource(id = R.drawable.ic_baseline_minus_24)
    val view = LocalView.current

    Row {
        Text (
            modifier = modifier
                .widthIn(max = 66.dp)
                .height(50.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            text = "$counter",
            style = textStyle.copy(fontFeatureSettings = "tnum"),
            textAlign = TextAlign.Center
        )
        AnimatedContent(
            targetState = enabled,
            transitionSpec = {
                (slideInHorizontally { fullWidth -> fullWidth } + fadeIn() with
                        slideOutHorizontally { fullWidth -> fullWidth } + fadeOut()).using(
                    SizeTransform(clip = false)
                )
            }
        ) { targetState ->
            if (targetState) {
                Row {
                    Box(
                        modifier = Modifier
                            .widthIn(max = 68.dp)
                            .fillMaxWidth(),
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
                        modifier = Modifier
                            .widthIn(max = 66.dp)
                            .fillMaxWidth(),
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
        }
        /*AnimatedVisibility(
            visible = enabled,
            enter = slideInHorizontally { fullWidth -> fullWidth },
            exit = slideOutHorizontally { fullWidth -> -fullWidth }
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .widthIn(max = 68.dp)
                        .fillMaxWidth(),
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
                    modifier = Modifier
                        .widthIn(max = 66.dp)
                        .fillMaxWidth(),
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
        }*/
    }



    /*AnimatedVisibility(
        visible = enabled,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        Row {
            Text (
                modifier = modifier
                    .widthIn(max = 66.dp)
                    .height(50.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = "$counter",
                style = textStyle.copy(fontFeatureSettings = "tnum"),
                textAlign = TextAlign.Center
            )
            Row (modifier = Modifier.animateEnterExit()) {
                Box(
                    modifier = Modifier
                        .widthIn(max = 68.dp)
                        .fillMaxWidth(),
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
                    modifier = Modifier
                        .widthIn(max = 66.dp)
                        .fillMaxWidth(),
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
    }*/

    /*Row(
        modifier = modifier
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text (
            modifier = modifier
                .widthIn(max = 66.dp)
                .height(50.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            text = "$counter",
            style = textStyle.copy(fontFeatureSettings = "tnum"),
            textAlign = TextAlign.Center
        )

        AnimatedContent(
            targetState = enabled,
            transitionSpec = {
                if (targetState) {
                    slideInHorizontally { fullWidth -> fullWidth } + fadeIn() with
                            ExitTransition.None
                } else {
                    EnterTransition.None with
                            slideOutHorizontally { fullWidth -> fullWidth } + fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }
        ) { targetState ->
            if (targetState) {
                Row {
                    Box(
                        modifier = Modifier
                            .widthIn(max = 68.dp)
                            .fillMaxWidth(),
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
                        modifier = Modifier
                            .widthIn(max = 66.dp)
                            .fillMaxWidth(),
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
        }

    }*/
}