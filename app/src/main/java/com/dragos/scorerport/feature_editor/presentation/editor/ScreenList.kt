package com.dragos.scorerport.feature_editor.presentation.editor

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dragos.scorerport.feature_editor.domain.model.MatchEnum
import com.dragos.scorerport.feature_editor.presentation.editor.componets.*

val screen: List<@Composable (modifier: Modifier) -> Unit> = listOf(
    { TextField(
        modifier = it,
        label = "Title",
        type = MatchEnum.Strings.TitleText
    )
    },
    {
        AllianceButtons(
            modifier = it,
            firstText = "Red Alliance",
            secondText = "Blue Alliance",
            type = MatchEnum.Ints.Alliance
        )
    },
    {
        Title(
            modifier = it,
            title = "Autonomous points: ",
            type = MatchEnum.Ints.AutoTitle
        )
    },
    {
        TextSwitch(
            modifier = it,
            text = "Duck delivery: ",
            type = MatchEnum.Booleans.AutoDuck
        )
    },
    {
        TextCounter(
            modifier = it,
            text = "Freight in storage: ",
            type = MatchEnum.Counters.AutoStorage
        )
    },
    {
        TextCounter(
            modifier = it,
            text = "Freight in hub L1: ",
            type = MatchEnum.Counters.AutoHubL1
        )
    },
    {
        TextCounter(
            modifier = it,
            text = "Freight in hub L2: ",
            type = MatchEnum.Counters.AutoHubL2
        )
    },
    {
        TextCounter(
            modifier = it,
            text = "Freight in hub L3: ",
            type = MatchEnum.Counters.AutoHubL3
        )
    },
    {
        TextButton(
            modifier = it,
            label = "Freight bonus: ",
            buttons = listOf("Duck", "Team element"),
            type = MatchEnum.Ints.AutoFreightBonus
        )
    },
    {
        TextButton(
            modifier = it,
            label = "Freight bonus 1: ",
            buttons = listOf("Duck", "Team element"),
            type = MatchEnum.Ints.AutoFreightBonus1
        )
    },
    {
        TextButton(
            modifier = it,
            label = "Freight bonus 2: ",
            buttons = listOf("Duck", "Team element"),
            type = MatchEnum.Ints.AutoFreightBonus2
        )
    },
    {
        TextButton(
            modifier = it,
            label = "Parked in : ",
            buttons = listOf("Storage", "Warehouse"),
            type = MatchEnum.Ints.AutoParked
        )
    },
    {
        TextSwitch(
            modifier = it,
            text = "Fully parked: ",
            type = MatchEnum.Booleans.AutoFullyParked
        )
    },
    {
        TextButton(
            modifier = it,
            label = "Parked in 1: ",
            buttons = listOf("Storage", "Warehouse"),
            type = MatchEnum.Ints.AutoParked1
        )
    },
    {
        TextSwitch(
            modifier = it,
            text = "Fully parked 1: ",
            type = MatchEnum.Booleans.AutoFullyParked1
        )
    },
    {
        TextButton(
            modifier = it,
            label = "Parked in 2: ",
            buttons = listOf("Storage", "Warehouse"),
            type = MatchEnum.Ints.AutoParked2
        )
    },
    {
        TextSwitch(
            modifier = it,
            text = "Fully parked 2: ",
            type = MatchEnum.Booleans.AutoFullyParked2
        )
    },
    {
        Title(
            modifier = it,
            title = "Driver points: ",
            type = MatchEnum.Ints.DriverTitle
        )
    },
    {
        TextCounter(
            modifier = it,
            text = "Freight in storage: ",
            type = MatchEnum.Counters.DriverStorage
        )
    },
    {
        TextCounter(
            modifier = it,
            text = "Freight in hub L1: ",
            type = MatchEnum.Counters.DriverHub1
        )
    },
    {
        TextCounter(
            modifier = it,
            text = "Freight in hub L2: ",
            type = MatchEnum.Counters.DriverHub2
        )
    },
    {
        TextCounter(
            modifier = it,
            text = "Freight in hub L3: ",
            type = MatchEnum.Counters.DriverHub3
        )
    },
    {
        TextCounter(
            modifier = it,
            text = "Freight in shared: ",
            type = MatchEnum.Counters.DriverShared
        )
    },
    {
        Title(
            modifier = it,
            title = "End points: ",
            type = MatchEnum.Ints.EndTitle
        )
    },
    {
        TextSwitch(
            modifier = it,
            text = "Balanced shipping hub: ",
            type = MatchEnum.Booleans.EndBalanced
        )
    },
    {
        TextSwitch(
            modifier = it,
            text = "Leaning shared hub: ",
            type = MatchEnum.Booleans.EndLeaning
        )
    },
    {
        TextCounter(
            modifier = it,
            text = "Carousel ducks: ",
            type = MatchEnum.Counters.EndDucks
        )
    },
    {
        TextButton(
            modifier = it,
            label = "Capping: ",
            buttons = listOf("Single", "Double"),
            type = MatchEnum.Ints.EndCapping
        )
    },
    {
        TextButton(
            modifier = it,
            label = "Parked: ",
            buttons = listOf("Partially", "Fully"),
            type = MatchEnum.Ints.EndParked
        )
    },
    {
        TextButton(
            modifier = it,
            label = "Parked 1: ",
            buttons = listOf("Partially", "Fully"),
            type = MatchEnum.Ints.EndParked1
        )
    },
    {
        TextButton(
            modifier = it,
            label = "Parked 2: ",
            buttons = listOf("Partially", "Fully"),
            type = MatchEnum.Ints.EndParked2
        )
    }
)