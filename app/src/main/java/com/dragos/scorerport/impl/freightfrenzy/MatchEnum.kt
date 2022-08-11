package com.dragos.scorerport.impl.freightfrenzy

import com.dragos.scorerport.impl.models.ItemEnum

class MatchEnum: ItemEnum {
    enum class Strings: ItemEnum.Strings {
        Title
    }
    enum class Booleans: ItemEnum.Booleans {
        AutoDuck,
        AutoFullyParked,
        EndBalanced,
        EndLeaning,
    }
    enum class Ints: ItemEnum.Ints {
        Alliance,
        AutoFreightBonus,
        AutoParked,
        EndParked,
        EndCapping,
        AutoTitle,
        DriverTitle,
        None
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