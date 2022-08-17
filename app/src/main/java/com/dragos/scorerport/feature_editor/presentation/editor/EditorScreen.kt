package com.dragos.scorerport.feature_editor.presentation.editor

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dragos.scorerport.feature_editor.presentation.editor.componets.*
import com.dragos.scorerport.impl.freightfrenzy.MatchEnum

val screen: List<@Composable () -> Unit> = listOf(
    { TextField(label = "Title", type = MatchEnum.Strings.TitleText) },
    { AllianceButtons(firstText = "Red Alliance", secondText = "Blue Alliance", type = MatchEnum.Ints.Alliance) },
    { Title(title = "Autonomous points: ", type = MatchEnum.Ints.AutoTitle) },
    { TextSwitch(text = "Duck delivery: ", type = MatchEnum.Booleans.AutoDuck) },
    { TextCounter(text = "Freight in storage: ", type = MatchEnum.Counters.AutoStorage) },
    { TextCounter(text = "Freight in hub L1: ", type = MatchEnum.Counters.AutoHubL1) },
    { TextCounter(text = "Freight in hub L2: ", type = MatchEnum.Counters.AutoHubL2) },
    { TextCounter(text = "Freight in hub L3: ", type = MatchEnum.Counters.AutoHubL3) },
    { TextButton(label = "Freight bonus: ", buttons = listOf("Duck", "Team element"), type = MatchEnum.Ints.AutoFreightBonus) },
    { TextButton(label = "Parked in: ", buttons = listOf("Storage", "Warehouse"), type = MatchEnum.Ints.AutoParked) },
    { TextSwitch(text = "Fully parked: ", type = MatchEnum.Booleans.AutoFullyParked) },
    { Title(title = "Driver points: ", type = MatchEnum.Ints.DriverTitle) },
    { TextCounter(text = "Freight in storage: ", type = MatchEnum.Counters.DriverStorage) },
    { TextCounter(text = "Freight in hub L1: ", type = MatchEnum.Counters.DriverHub1) },
    { TextCounter(text = "Freight in hub L2: ", type = MatchEnum.Counters.DriverHub2) },
    { TextCounter(text = "Freight in hub L3: ", type = MatchEnum.Counters.DriverHub3) },
    { TextCounter(text = "Freight in shared: ", type = MatchEnum.Counters.DriverShared) },
    { Title(title = "End points: ", type = MatchEnum.Ints.EndTitle) },
    { TextSwitch(text = "Balanced shipping hub: ", type = MatchEnum.Booleans.EndBalanced) },
    { TextSwitch(text = "Leaning shared hub: ", type = MatchEnum.Booleans.EndLeaning) },
    { TextCounter(text = "Carousel ducks: ", type = MatchEnum.Counters.EndDucks) },
    { TextButton(label = "Capping: ", buttons = listOf("Single", "Double"), type = MatchEnum.Ints.EndCapping) },
    { TextButton(label = "Parked: ", buttons = listOf("Partially", "Fully"), type = MatchEnum.Ints.EndParked) },
    { Title(title = "Total points: ", type = MatchEnum.Ints.TotalTitle) }
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen (

) {
    Scaffold { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) { items(items = screen) { item -> item() } }
    }
}