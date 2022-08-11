package com.dragos.scorerport.impl.freightfrenzy

import com.dragos.scorerport.impl.models.Screen
import com.dragos.scorerport.impl.models.ScreenElements

class MatchScreen: Screen {
    override val elements = listOf(
        ScreenElements.Title(MatchEnum.Ints.None, "New Match", largeTitle = true),
        ScreenElements.TextField(MatchEnum.Strings.Title, "Title"),
        ScreenElements.AllianceButtons(MatchEnum.Ints.Alliance, "Red Alliance", "Blue Alliance"),
        ScreenElements.Title(MatchEnum.Ints.AutoTitle, "Autonomous: ", true),
        ScreenElements.Switch(MatchEnum.Booleans.AutoDuck, "Duck delivery: "),
        ScreenElements.SegmentedButton(MatchEnum.Ints.AutoFreightBonus, "Freight Bonus: ", listOf("Duck", "Team\nelement")),
        ScreenElements.Counter(MatchEnum.Counters.AutoStorage, "Freight in storage: "),
        ScreenElements.Counter(MatchEnum.Counters.AutoHubL1, "Freight in hub L1: "),
        ScreenElements.Counter(MatchEnum.Counters.AutoHubL2, "Freight in hub L2: "),
        ScreenElements.Counter(MatchEnum.Counters.AutoHubL3, "Freight in hub L3: "),
        ScreenElements.SegmentedButton(MatchEnum.Ints.AutoParked, "Parked in: ", listOf("Storage", "Warehouse")),
        ScreenElements.Switch(MatchEnum.Booleans.AutoFullyParked, "Fully parked: "),
        ScreenElements.Title(MatchEnum.Ints.DriverTitle, "Driver: ", counter = true),
        ScreenElements.Counter(MatchEnum.Counters.DriverStorage, "Freight in storage: "),
        ScreenElements.Counter(MatchEnum.Counters.DriverHub1, "Freight in hub L1: "),
        ScreenElements.Counter(MatchEnum.Counters.DriverHub2, "Freight in hub L2: "),
        ScreenElements.Counter(MatchEnum.Counters.DriverHub3, "Freight in hub L3: "),
        ScreenElements.Counter(MatchEnum.Counters.DriverShared, "Freight in shared: "),
    )
}