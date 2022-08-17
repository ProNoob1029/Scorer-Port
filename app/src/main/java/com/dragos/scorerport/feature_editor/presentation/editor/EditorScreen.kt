package com.dragos.scorerport.feature_editor.presentation.editor

import android.view.HapticFeedbackConstants
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dragos.scorerport.feature_editor.presentation.editor.componets.*
import com.dragos.scorerport.impl.freightfrenzy.MatchEnum

val screen: List<@Composable (modifier: Modifier) -> Unit> = listOf(
    { TextField(modifier = it, label = "Title", type = MatchEnum.Strings.TitleText) },
    { AllianceButtons(modifier = it, firstText = "Red Alliance", secondText = "Blue Alliance", type = MatchEnum.Ints.Alliance) },
    { Title(modifier = it, title = "Autonomous points: ", type = MatchEnum.Ints.AutoTitle) },
    { TextSwitch(modifier = it, text = "Duck delivery: ", type = MatchEnum.Booleans.AutoDuck) },
    { TextCounter(modifier = it, text = "Freight in storage: ", type = MatchEnum.Counters.AutoStorage) },
    { TextCounter(modifier = it, text = "Freight in hub L1: ", type = MatchEnum.Counters.AutoHubL1) },
    { TextCounter(modifier = it, text = "Freight in hub L2: ", type = MatchEnum.Counters.AutoHubL2) },
    { TextCounter(modifier = it, text = "Freight in hub L3: ", type = MatchEnum.Counters.AutoHubL3) },
    { TextButton(modifier = it, label = "Freight bonus: ", buttons = listOf("Duck", "Team element"), type = MatchEnum.Ints.AutoFreightBonus) },
    { TextButton(modifier = it, label = "Freight bonus 1: ", buttons = listOf("Duck", "Team element"), type = MatchEnum.Ints.AutoFreightBonus1) },
    { TextButton(modifier = it, label = "Freight bonus 2: ", buttons = listOf("Duck", "Team element"), type = MatchEnum.Ints.AutoFreightBonus2) },
    { TextButton(modifier = it, label = "Parked in : ", buttons = listOf("Storage", "Warehouse"), type = MatchEnum.Ints.AutoParked) },
    { TextSwitch(modifier = it, text = "Fully parked: ", type = MatchEnum.Booleans.AutoFullyParked) },
    { TextButton(modifier = it, label = "Parked in 1: ", buttons = listOf("Storage", "Warehouse"), type = MatchEnum.Ints.AutoParked1) },
    { TextSwitch(modifier = it, text = "Fully parked 1: ", type = MatchEnum.Booleans.AutoFullyParked1) },
    { TextButton(modifier = it, label = "Parked in 2: ", buttons = listOf("Storage", "Warehouse"), type = MatchEnum.Ints.AutoParked2) },
    { TextSwitch(modifier = it, text = "Fully parked 2: ", type = MatchEnum.Booleans.AutoFullyParked2) },
    { Title(modifier = it, title = "Driver points: ", type = MatchEnum.Ints.DriverTitle) },
    { TextCounter(modifier = it, text = "Freight in storage: ", type = MatchEnum.Counters.DriverStorage) },
    { TextCounter(modifier = it, text = "Freight in hub L1: ", type = MatchEnum.Counters.DriverHub1) },
    { TextCounter(modifier = it, text = "Freight in hub L2: ", type = MatchEnum.Counters.DriverHub2) },
    { TextCounter(modifier = it, text = "Freight in hub L3: ", type = MatchEnum.Counters.DriverHub3) },
    { TextCounter(modifier = it, text = "Freight in shared: ", type = MatchEnum.Counters.DriverShared) },
    { Title(modifier = it, title = "End points: ", type = MatchEnum.Ints.EndTitle) },
    { TextSwitch(modifier = it, text = "Balanced shipping hub: ", type = MatchEnum.Booleans.EndBalanced) },
    { TextSwitch(modifier = it, text = "Leaning shared hub: ", type = MatchEnum.Booleans.EndLeaning) },
    { TextCounter(modifier = it, text = "Carousel ducks: ", type = MatchEnum.Counters.EndDucks) },
    { TextButton(modifier = it, label = "Capping: ", buttons = listOf("Single", "Double"), type = MatchEnum.Ints.EndCapping) },
    { TextButton(modifier = it, label = "Parked: ", buttons = listOf("Partially", "Fully"), type = MatchEnum.Ints.EndParked) },
    { TextButton(modifier = it, label = "Parked 1: ", buttons = listOf("Partially", "Fully"), type = MatchEnum.Ints.EndParked1) },
    { TextButton(modifier = it, label = "Parked 2: ", buttons = listOf("Partially", "Fully"), type = MatchEnum.Ints.EndParked2) },
    { Title(modifier = it, title = "Total points: ", type = MatchEnum.Ints.TotalTitle) }
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun EditorScreen(
    viewModel: EditorViewModel = hiltViewModel()
) {
    val checked by rememberSaveable { viewModel.get(MatchEnum.Booleans.Title) }

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

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar (
                navigationIcon = {
                    Surface(
                        checked = checked,
                        onCheckedChange = {
                            view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                            viewModel.set(MatchEnum.Booleans.Title, it)
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
                        style = MaterialTheme.typography.headlineMedium
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
                        Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
                    }
                },
                colors = appBarColors,
            )
        },
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) { items(items = screen) { item -> item(Modifier.animateItemPlacement()) } }
    }
}