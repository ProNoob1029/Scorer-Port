package com.dragos.scorerport.feature_editor.domain.model

class MatchEnum {
    enum class Strings {
        TitleText
    }
    enum class Booleans {
        AutoDuck,
        AutoFullyParked,
        AutoFullyParked1,
        AutoFullyParked2,
        EndBalanced,
        EndLeaning,
        Title
    }
    enum class Ints {
        Alliance,
        AutoFreightBonus,
        AutoFreightBonus1,
        AutoFreightBonus2,
        AutoParked,
        AutoParked1,
        AutoParked2,
        EndParked,
        EndParked1,
        EndParked2,
        EndCapping,
        AutoTitle,
        DriverTitle,
        EndTitle,
        TotalTitle
    }
    enum class Counters {
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