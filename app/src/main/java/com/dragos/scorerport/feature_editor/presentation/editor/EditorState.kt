package com.dragos.scorerport.feature_editor.presentation.editor

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.dragos.scorerport.impl.freightfrenzy.MatchEnum
import com.dragos.scorerport.impl.freightfrenzy.MatchModel

class EditorState(
    match: MatchModel = MatchModel()
) {
    private val title = mutableStateOf(match.title)
    private val alliance = mutableStateOf(match.alliance)
    private val autoDuck = mutableStateOf(match.autoDuck)
    private val autoStorage = mutableStateOf(match.autoStorage)
    private val autoHub1 = mutableStateOf(match.autoHub1)
    private val autoHub2 = mutableStateOf(match.autoHub2)
    private val autoHub3 = mutableStateOf(match.autoHub3)
    private val autoFreightBonus1 = mutableStateOf(match.autoFreightBonus1)
    private val autoFreightBonus2 = mutableStateOf(match.autoFreightBonus2)
    private val autoParked1 = mutableStateOf(match.autoParked1)
    private val autoParked2 = mutableStateOf(match.autoParked2)
    private val autoFullyParked1 = mutableStateOf(match.autoFullyParked1)
    private val autoFullyParked2 = mutableStateOf(match.autoFullyParked2)
    private val driverStorage = mutableStateOf(match.driverStorage)
    private val driverHub1 = mutableStateOf(match.driverHub1)
    private val driverHub2 = mutableStateOf(match.driverHub2)
    private val driverHub3 = mutableStateOf(match.driverHub3)
    private val driverShared = mutableStateOf(match.driverShared)
    private val endDucks = mutableStateOf(match.endDucks)
    private val endBalanced = mutableStateOf(match.endBalanced)
    private val endLeaning = mutableStateOf(match.endLeaning)
    private val endParked1 = mutableStateOf(match.endParked1)
    private val endParked2 = mutableStateOf(match.endParked2)
    private val endCapping = mutableStateOf(match.endCapping)

    private val team2 = mutableStateOf(match.team2)
    //private val team1 = mutableStateOf(!match.team2)

    private val autoPoints = mutableStateOf(0)
    private val driverPoints = mutableStateOf(0)
    private val endPoints = mutableStateOf(0)
    private val totalPoints = mutableStateOf(0)


    fun set(type: MatchEnum.Strings, value: String) {
        when(type) {
            MatchEnum.Strings.TitleText -> title.value = value
        }
    }

    fun set(type: MatchEnum.Booleans, value: Boolean) {
        when(type) {
            MatchEnum.Booleans.Title -> team2.value = value
            MatchEnum.Booleans.AutoDuck -> autoDuck.value = value
            MatchEnum.Booleans.AutoFullyParked -> autoFullyParked1.value = value
            MatchEnum.Booleans.AutoFullyParked1 -> autoFullyParked1.value = value
            MatchEnum.Booleans.AutoFullyParked2 -> autoFullyParked2.value = value
            MatchEnum.Booleans.EndBalanced -> endBalanced.value = value
            MatchEnum.Booleans.EndLeaning -> endLeaning.value = value
        }
    }

    fun set(type: MatchEnum.Ints, value: Int) {
        when(type) {
            MatchEnum.Ints.Alliance -> alliance.value = value
            MatchEnum.Ints.AutoTitle -> autoPoints.value = value
            MatchEnum.Ints.AutoFreightBonus -> autoFreightBonus1.value = value
            MatchEnum.Ints.AutoFreightBonus1 -> autoFreightBonus1.value = value
            MatchEnum.Ints.AutoFreightBonus2 -> autoFreightBonus2.value = value
            MatchEnum.Ints.AutoParked -> autoParked1.value = value
            MatchEnum.Ints.AutoParked1 -> autoParked1.value = value
            MatchEnum.Ints.AutoParked2 -> autoParked2.value = value
            MatchEnum.Ints.DriverTitle -> driverPoints.value = value
            MatchEnum.Ints.EndTitle -> endPoints.value = value
            MatchEnum.Ints.EndCapping -> endCapping.value = value
            MatchEnum.Ints.EndParked -> endParked1.value = value
            MatchEnum.Ints.EndParked1 -> endParked1.value = value
            MatchEnum.Ints.EndParked2 -> endParked2.value = value
            MatchEnum.Ints.TotalTitle -> totalPoints.value = value
        }
    }

    fun set(type: MatchEnum.Counters, value: Int) {
        when(type) {
            MatchEnum.Counters.AutoStorage -> autoStorage.value = value
            MatchEnum.Counters.AutoHubL1 -> autoHub1.value = value
            MatchEnum.Counters.AutoHubL2 -> autoHub2.value = value
            MatchEnum.Counters.AutoHubL3 -> autoHub3.value = value
            MatchEnum.Counters.DriverStorage -> driverStorage.value = value
            MatchEnum.Counters.DriverHub1 -> driverHub1.value = value
            MatchEnum.Counters.DriverHub2 -> driverHub2.value = value
            MatchEnum.Counters.DriverHub3 -> driverHub3.value = value
            MatchEnum.Counters.DriverShared -> driverShared.value = value
            MatchEnum.Counters.EndDucks -> endDucks.value = value
        }
    }

    fun add(type: MatchEnum.Counters, add: Int) {
        when(type) {
            MatchEnum.Counters.AutoStorage -> autoStorage.value += add
            MatchEnum.Counters.AutoHubL1 -> autoHub1.value += add
            MatchEnum.Counters.AutoHubL2 -> autoHub2.value += add
            MatchEnum.Counters.AutoHubL3 -> autoHub3.value += add
            MatchEnum.Counters.DriverStorage -> driverStorage.value += add
            MatchEnum.Counters.DriverHub1 -> driverHub1.value += add
            MatchEnum.Counters.DriverHub2 -> driverHub2.value += add
            MatchEnum.Counters.DriverHub3 -> driverHub3.value += add
            MatchEnum.Counters.DriverShared -> driverShared.value += add
            MatchEnum.Counters.EndDucks -> endDucks.value += add
        }
    }

    fun get(type: MatchEnum.Strings): MutableState<String> {
        return when(type) {
            MatchEnum.Strings.TitleText -> title
        }
    }

    fun get(type: MatchEnum.Booleans): MutableState<Boolean> {
        return when(type) {
            MatchEnum.Booleans.Title -> team2
            MatchEnum.Booleans.AutoDuck -> autoDuck
            MatchEnum.Booleans.AutoFullyParked -> autoFullyParked1
            MatchEnum.Booleans.AutoFullyParked1 -> autoFullyParked1
            MatchEnum.Booleans.AutoFullyParked2 -> autoFullyParked2
            MatchEnum.Booleans.EndBalanced -> endBalanced
            MatchEnum.Booleans.EndLeaning -> endLeaning
        }
    }

    fun get(type: MatchEnum.Ints): MutableState<Int> {
        return when(type) {
            MatchEnum.Ints.Alliance -> alliance
            MatchEnum.Ints.AutoTitle -> autoPoints
            MatchEnum.Ints.AutoFreightBonus -> autoFreightBonus1
            MatchEnum.Ints.AutoFreightBonus1 -> autoFreightBonus1
            MatchEnum.Ints.AutoFreightBonus2 -> autoFreightBonus2
            MatchEnum.Ints.AutoParked -> autoParked1
            MatchEnum.Ints.AutoParked1 -> autoParked1
            MatchEnum.Ints.AutoParked2 -> autoParked2
            MatchEnum.Ints.DriverTitle -> driverPoints
            MatchEnum.Ints.EndTitle -> driverPoints
            MatchEnum.Ints.EndCapping -> endCapping
            MatchEnum.Ints.EndParked -> endParked1
            MatchEnum.Ints.EndParked1 -> endParked1
            MatchEnum.Ints.EndParked2 -> endParked2
            MatchEnum.Ints.TotalTitle -> totalPoints
        }
    }

    fun get(type: MatchEnum.Counters): MutableState<Int> {
        return when(type) {
            MatchEnum.Counters.AutoStorage -> autoStorage
            MatchEnum.Counters.AutoHubL1 -> autoHub1
            MatchEnum.Counters.AutoHubL2 -> autoHub2
            MatchEnum.Counters.AutoHubL3 -> autoHub3
            MatchEnum.Counters.DriverStorage -> driverStorage
            MatchEnum.Counters.DriverHub1 -> driverHub1
            MatchEnum.Counters.DriverHub2 -> driverHub2
            MatchEnum.Counters.DriverHub3 -> driverHub3
            MatchEnum.Counters.DriverShared -> driverShared
            MatchEnum.Counters.EndDucks -> endDucks
        }
    }
}