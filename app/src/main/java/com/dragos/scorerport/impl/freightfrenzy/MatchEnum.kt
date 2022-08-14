package com.dragos.scorerport.impl.freightfrenzy

import com.dragos.scorerport.impl.models.ItemEnum

class MatchEnum: ItemEnum {
    enum class Strings: ItemEnum.Strings {
        TitleText
    }
    enum class Booleans: ItemEnum.Booleans {
        AutoDuck,
        AutoFullyParked,
        AutoFullyParked1,
        AutoFullyParked2,
        EndBalanced,
        EndLeaning,
        Title
    }
    enum class Ints: ItemEnum.Ints {
        Alliance,
        AutoFreightBonus,
        AutoFreightBonus1,
        AutoFreightBonus2,
        AutoParked,
        AutoParked1,
        AutoParked2,
        EndParked,
        EndCapping,
        AutoTitle,
        DriverTitle,
        EndTitle,
        TotalTitle
    }
    enum class Counters: ItemEnum.Counters {
        AutoStorage,
        AutoHubL1,
        AutoHubL2,
        AutoHubL3,
        DriverStorage,
        DriverHub1,
        DriverHub2,
        DriverHub3,
        DriverShared,
        EndDucks,
    }
}