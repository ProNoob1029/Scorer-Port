package com.dragos.scorerport.impl.freightfrenzy

import com.dragos.scorerport.impl.models.Screen
import com.dragos.scorerport.impl.models.ScreenElements

class MatchScreen: Screen {
    override val elements = listOf(
        ScreenElements.TextField(MatchEnum.Strings.TitleText, "Title"),
        ScreenElements.AllianceButtons(MatchEnum.Ints.Alliance, "Red Alliance", "Blue Alliance"),
        ScreenElements.Title(MatchEnum.Ints.AutoTitle, "Autonomous: ", true),
        ScreenElements.Switch(MatchEnum.Booleans.AutoDuck, "Duck delivery: "),
        ScreenElements.Counter(MatchEnum.Counters.AutoStorage, "Freight in storage: "),
        ScreenElements.Counter(MatchEnum.Counters.AutoHubL1, "Freight in hub L1: "),
        ScreenElements.Counter(MatchEnum.Counters.AutoHubL2, "Freight in hub L2: "),
        ScreenElements.Counter(MatchEnum.Counters.AutoHubL3, "Freight in hub L3: "),
        ScreenElements.SegmentedButton(MatchEnum.Ints.AutoFreightBonus, "Freight Bonus: ", listOf("Duck", "Team\nelement")),
        ScreenElements.SegmentedButton(MatchEnum.Ints.AutoFreightBonus1, "Freight Bonus 1: ", listOf("Duck", "Team\nelement")),
        ScreenElements.SegmentedButton(MatchEnum.Ints.AutoFreightBonus2, "Freight Bonus 2: ", listOf("Duck", "Team\nelement")),
        ScreenElements.SegmentedButton(MatchEnum.Ints.AutoParked, "Parked in: ", listOf("Storage", "Warehouse")),
        ScreenElements.Switch(MatchEnum.Booleans.AutoFullyParked, "Fully parked: "),
        ScreenElements.SegmentedButton(MatchEnum.Ints.AutoParked1, "Parked in 1: ", listOf("Storage", "Warehouse")),
        ScreenElements.Switch(MatchEnum.Booleans.AutoFullyParked1, "Fully parked 1: "),
        ScreenElements.SegmentedButton(MatchEnum.Ints.AutoParked2, "Parked in 2: ", listOf("Storage", "Warehouse")),
        ScreenElements.Switch(MatchEnum.Booleans.AutoFullyParked2, "Fully parked 2: "),
        ScreenElements.Title(MatchEnum.Ints.DriverTitle, "Driver: ", true),
        ScreenElements.Counter(MatchEnum.Counters.DriverStorage, "Freight in storage: "),
        ScreenElements.Counter(MatchEnum.Counters.DriverHub1, "Freight in hub L1: "),
        ScreenElements.Counter(MatchEnum.Counters.DriverHub2, "Freight in hub L2: "),
        ScreenElements.Counter(MatchEnum.Counters.DriverHub3, "Freight in hub L3: "),
        ScreenElements.Counter(MatchEnum.Counters.DriverShared, "Freight in shared: "),
        ScreenElements.Title(MatchEnum.Ints.EndTitle, "Endgame: ", true),
        ScreenElements.Switch(MatchEnum.Booleans.EndBalanced, "Balanced shipping hub: "),
        ScreenElements.Switch(MatchEnum.Booleans.EndLeaning, "Leaning shared hub: "),
        ScreenElements.Counter(MatchEnum.Counters.EndDucks, "Carousel ducks: "),
        ScreenElements.SegmentedButton(MatchEnum.Ints.EndCapping, "Capping: ", listOf("Single", "Double")),
        ScreenElements.SegmentedButton(MatchEnum.Ints.EndParked, "Parked: ", listOf("Partially","Fully")),
        ScreenElements.SegmentedButton(MatchEnum.Ints.EndParked1, "Parked 1: ", listOf("Partially","Fully")),
        ScreenElements.SegmentedButton(MatchEnum.Ints.EndParked2, "Parked 2: ", listOf("Partially","Fully")),
        ScreenElements.Title(MatchEnum.Ints.TotalTitle, "Total: ", true)
    )
    override val title = "New Match"
    override val title1 = "1 Team"
    override val title2 = "2 Teams"
    override val titleType = MatchEnum.Booleans.Title
}