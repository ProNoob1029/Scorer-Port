package com.dragos.scorerport.feature_editor.presentation.edit

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.presentation.edit.components.*
import com.dragos.scorerport.feature_editor.presentation.util.OutlinedTextField
import com.dragos.scorerport.impl.models.ScreenElements
import com.dragos.scorerport.ui.theme.padding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    viewModel: EditViewModel = hiltViewModel(),
) {
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        decayAnimationSpec,
        rememberTopAppBarState()
    )

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar (
                navigationIcon = {},
                title = {
                    Text(
                        text = viewModel.screen.title
                    )
                },
                scrollBehavior = scrollBehavior
            )
        }
            ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(
                items = viewModel.screen.elements
            ) { item ->
                when(item) {
                    /*is ScreenElements.Header -> {
                        val value by rememberSaveable { viewModel.getState(item.type) }

                        val containerColor by animateColorAsState(
                            targetValue = if (value)
                                MaterialTheme.colorScheme.tertiary
                            else MaterialTheme.colorScheme.primary
                        )

                        val contentColor by animateColorAsState(
                            targetValue = if (value)
                                contentColorFor(backgroundColor = MaterialTheme.colorScheme.tertiary)
                            else contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
                        )

                        TitleCard(
                            title = item.title,
                            titleStyle = MaterialTheme.typography.headlineLarge
                        ) {
                            Button(
                                onClick = { viewModel.setState(item.type, !value) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = containerColor,
                                    contentColor = contentColor,
                                )
                            ) {
                                Text(
                                    text = if (value) item.title2 else item.title1
                                )
                            }
                        }
                    }*/
                    is ScreenElements.Title -> {
                        //val value by viewModel.getState(item.type)
                        //val value = 0
                        val value by rememberSaveable { viewModel.getState(item.type) }

                        TitleCard(
                            modifier = Modifier.padding(top = 8.dp),
                            title = item.title,
                            points = if (item.counter) value else null,
                            titleStyle = null
                        )
                    }
                    is ScreenElements.TextField -> {
                        //val value by viewModel.getState(item.type)
                        //val value = ""
                        val value by rememberSaveable { viewModel.getState(item.type) }
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(MaterialTheme.padding.inRow),
                            value = value,
                            onValueChange = { viewModel.setState(item.type, it) },
                            label = {
                                Text(
                                    text = item.label,
                                )
                            },
                            keyboardOptions = KeyboardOptions(autoCorrect = false),
                            textStyle = MaterialTheme.typography.titleLarge,
                            labelBodySmall = MaterialTheme.typography.titleMedium,
                        )
                    }
                    is ScreenElements.AllianceButtons -> {
                        //val activeIndex by viewModel.getState(item.type)
                        //val activeIndex = 0
                        val activeIndex by rememberSaveable { viewModel.getState(item.type) }
                        AllianceButtons(
                            modifier = Modifier
                                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp),
                            activeIndex = activeIndex,
                            onItemClick = { index ->
                                viewModel.setState(
                                    item.type,
                                    value = if (activeIndex == index)
                                        0
                                    else index
                                )
                            },
                            redText= item.text1,
                            blueText= item.text2,
                        )
                    }
                    is ScreenElements.Switch -> {
                        //val checked by viewModel.getState(item.type)
                        //val checked = false
                        val checked by rememberSaveable { viewModel.getState(item.type) }

                        val visible by rememberSaveable { viewModel.getVisibility(item.type) }

                        val animatedVisible by rememberSaveable { viewModel.getAnimatedVisibility(item.type) }

                        val specialColor = rememberSaveable { viewModel.specialColor(item.type) }

                        val colors = if (specialColor)
                            SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.onTertiary,
                                checkedTrackColor = MaterialTheme.colorScheme.tertiary,
                                checkedBorderColor = MaterialTheme.colorScheme.tertiary
                            )
                        else
                            SwitchDefaults.colors()

                        if (visible) {
                            AnimatedVisibility(
                                visible = animatedVisible,
                                enter = fadeIn() + slideInVertically(),
                                exit = fadeOut() + slideOutVertically()
                            ) {
                                TextSwitch(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .padding(top = 8.dp),
                                    checked = checked,
                                    onCheckedChange = { viewModel.setState(item.type, it) },
                                    text = item.label,
                                    colors = colors,
                                )
                            }
                        }
                    }
                    is ScreenElements.Counter -> {
                        //val counter by viewModel.getState(item.type)
                        //val counter = 0
                        val counter by rememberSaveable { viewModel.getState(item.type) }
                        TextCounter(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(top = 8.dp),
                            counter = counter,
                            text = item.label,
                            onClickPlus = { viewModel.setState(item.type, 1) },
                            onClickMinus = { viewModel.setState(item.type, -1) },
                            plusEnabled = true,
                            minusEnabled = counter > 0
                        )
                    }
                    is ScreenElements.SegmentedButton -> {
                        //val selectedIndex by viewModel.getState(item.type)
                        //val selectedIndex = 0
                        val selectedIndex by rememberSaveable { viewModel.getState(item.type) }

                        val visibility by rememberSaveable { viewModel.getVisibility(item.type) }

                        val specialColor = rememberSaveable { viewModel.specialColor(item.type) }

                        val color = if (specialColor) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary

                        if (visibility) {
                            TextButtons(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(top = 8.dp),
                                items = item.buttons,
                                label = item.label,
                                selectedIndex = selectedIndex,
                                onItemClick = { index ->
                                    viewModel.setState(
                                        item.type,
                                        value = if (index + 1 == selectedIndex) 0 else index + 1
                                    )
                                },
                                color = color
                            )
                        }
                    }
                }
            }
        }
    }
}