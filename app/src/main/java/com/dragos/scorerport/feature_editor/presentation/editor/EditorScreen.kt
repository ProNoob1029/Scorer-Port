package com.dragos.scorerport.feature_editor.presentation.editor

import android.view.HapticFeedbackConstants
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.domain.model.MatchEnum
import com.dragos.scorerport.feature_editor.presentation.editor.componets.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

data class EditorArgs(val key: String?)

@Destination(
    navArgsDelegate = EditorArgs::class,
)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun EditorScreen(
    viewModel: EditorViewModel = hiltViewModel(),
    destinationsNavigator: DestinationsNavigator
) {
    val checked by rememberSaveable { viewModel.state.get(MatchEnum.Booleans.Title) }

    val color by animateColorAsState(
        targetValue = if (checked) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.primaryContainer
    )

    val strokeColor by animateColorAsState(
        targetValue = if (checked) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
    )

    val appBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primaryContainer)
    )

    val iconColors = IconButtonDefaults.iconButtonColors(
        contentColor = contentColorFor(
            backgroundColor = MaterialTheme.colorScheme.primaryContainer
        )
    )

    val view = LocalView.current

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(
        rememberTopAppBarState()
    )

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is EditorViewModel.UiEvent.NavigateUp -> {
                    destinationsNavigator.navigateUp()
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                    //viewModel.save()
                    viewModel.setEnabled(!viewModel.editEnabled.value)
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Filled.Done, contentDescription = "Done")
            }

        },
        topBar = {
            CenterAlignedTopAppBar (
                navigationIcon = {
                    Surface(
                        checked = checked,
                        onCheckedChange = {
                            view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                            viewModel.state.set(MatchEnum.Booleans.Title, it)
                        },
                        shape = CircleShape,
                        color = color,
                        border = BorderStroke(1.dp, strokeColor)
                    ) {
                        Text(
                            modifier = Modifier
                                .size(32.dp)
                                .wrapContentHeight(),
                            text = if (checked) "2" else "1",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                title = {
                    Text(
                        text = "New Match",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                            viewModel.reset()
                        },
                        colors = iconColors
                    ) {
                        Icon(Icons.Filled.Refresh, contentDescription = "Reset", modifier = Modifier.size(32.dp))
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = appBarColors
            )
        },
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = screen) { item -> item(Modifier.animateItemPlacement()) }
            item {
                Surface(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier
                        .animateItemPlacement()
                        .padding(top = 8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Title(
                            modifier = Modifier.weight(1f),
                            paddingValues = PaddingValues(),
                            title = "Total points: ",
                            type = MatchEnum.Ints.TotalTitle
                        )
                        Spacer(modifier = Modifier.size(88.dp))
                    }
                }
            }
        }
    }
}