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

    val team2 = mutableStateOf(match.team2)
    val team1 = mutableStateOf(!match.team2)

    private val autoPoints = mutableStateOf(0)
    private val driverPoints = mutableStateOf(0)
    private val endPoints = mutableStateOf(0)
    private val totalPoints = mutableStateOf(0)

    val autoFullyParkedVisible1 = mutableStateOf(match.autoParked1 != 0)
    val autoFullyParkedVisible2 = mutableStateOf(match.autoParked2 != 0)

    fun set(item: MatchModel) {
        title.value = item.title
        alliance.value = item.alliance
        autoDuck.value = item.autoDuck
        autoStorage.value = item.autoStorage
        autoHub1.value = item.autoHub1
        autoHub2.value = item.autoHub2
        autoHub3.value = item.autoHub3
        autoFreightBonus1.value = item.autoFreightBonus1
        autoFreightBonus2.value = item.autoFreightBonus2
        autoParked1.value = item.autoParked1
        autoParked2.value = item.autoParked2
        autoFullyParked1.value = item.autoFullyParked1
        autoFullyParked2.value = item.autoFullyParked2
        driverStorage.value = item.driverStorage
        driverHub1.value = item.driverHub1
        driverHub2.value = item.driverHub2
        driverHub3.value = item.driverHub3
        driverShared.value = item.driverShared
        endDucks.value = item.endDucks
        endBalanced.value = item.endBalanced
        endLeaning.value = item.endLeaning
        endParked1.value = item.endParked1
        endParked2.value = item.endParked2
        endCapping.value = item.endCapping
        team2.value = item.team2
        team1.value = !item.team2

        autoFullyParkedVisible1.value = item.autoParked1 != 0
        autoFullyParkedVisible2.value = item.autoParked2 != 0

        calculatePoints()
    }

    init {
        calculatePoints()
    }

    private fun calculatePoints() {
        calculateAutoPoints()
        calculateDriverPoints()
        calculateEndPoints()
        calculateTotal()
    }

    private fun calculateAutoPoints() {
        val autoHub = autoHub1.value + autoHub2.value + autoHub3.value
        autoPoints.value =
            autoDuck.value.toInt() * 10 +
                    autoFreightBonus1.value * 10 +
                    autoFreightBonus2.value * 10 * team2.value.toInt() +
                    autoStorage.value * 2 + autoHub * 6 +
                    when(autoParked1.value) {
                        1 -> 3
                        2 -> 5
                        else -> 0
                    } * (autoFullyParked1.value.toInt() + 1) +
                    when(autoParked2.value) {
                        1 -> 3
                        2 -> 5
                        else -> 0
                    } * (autoFullyParked2.value.toInt() + 1) * team2.value.toInt()
    }
    private fun calculateDriverPoints() {
        driverPoints.value =
            driverStorage.value +
                    driverHub1.value * 2 +
                    driverHub2.value * 4 +
                    driverHub3.value * 6 +
                    driverShared.value * 4
    }
    private fun calculateEndPoints() {
        endPoints.value =
            endDucks.value * 6 +
                    endBalanced.value.toInt() * 10 +
                    endLeaning.value.toInt() * 20 +
                    endCapping.value * 15 +
                    endParked1.value * 3 +
                    endParked2.value * 3 * team2.value.toInt()
    }
    private fun calculateTotal() {
        totalPoints.value = autoPoints.value + driverPoints.value + endPoints.value
    }

    fun set(type: MatchEnum.Strings, value: String) {
        when(type) {
            MatchEnum.Strings.TitleText -> title.value = value
        }
    }

    fun set(type: MatchEnum.Booleans, value: Boolean) {
        when(type) {
            MatchEnum.Booleans.Title -> {
                team2.value = value
                team1.value = !value
                calculateAutoPoints()
                calculateEndPoints()
            }
            MatchEnum.Booleans.AutoDuck -> {
                autoDuck.value = value
                calculateAutoPoints()
            }
            MatchEnum.Booleans.AutoFullyParked -> {
                autoFullyParked1.value = value
                calculateAutoPoints()
            }
            MatchEnum.Booleans.AutoFullyParked1 -> {
                autoFullyParked1.value = value
                calculateAutoPoints()
            }
            MatchEnum.Booleans.AutoFullyParked2 -> {
                autoFullyParked2.value = value
                calculateAutoPoints()
            }
            MatchEnum.Booleans.EndBalanced -> {
                endBalanced.value = value
                calculateEndPoints()
            }
            MatchEnum.Booleans.EndLeaning -> {
                endLeaning.value = value
                calculateEndPoints()
            }
        }
        calculateTotal()
    }

    fun set(type: MatchEnum.Ints, value: Int) {
        when(type) {
            MatchEnum.Ints.Alliance -> alliance.value = value
            MatchEnum.Ints.AutoTitle -> autoPoints.value = value
            MatchEnum.Ints.AutoFreightBonus -> {
                autoFreightBonus1.value = value
                calculateAutoPoints()
                calculateTotal()
            }
            MatchEnum.Ints.AutoFreightBonus1 -> {
                autoFreightBonus1.value = value
                calculateAutoPoints()
                calculateTotal()
            }
            MatchEnum.Ints.AutoFreightBonus2 -> {
                autoFreightBonus2.value = value
                calculateAutoPoints()
                calculateTotal()
            }
            MatchEnum.Ints.AutoParked -> {
                autoParked1.value = value
                calculateAutoPoints()
                calculateTotal()
            }
            MatchEnum.Ints.AutoParked1 -> {
                autoParked1.value = value
                calculateAutoPoints()
                calculateTotal()
            }
            MatchEnum.Ints.AutoParked2 -> {
                autoParked2.value = value
                calculateAutoPoints()
                calculateTotal()
            }
            MatchEnum.Ints.DriverTitle -> driverPoints.value = value
            MatchEnum.Ints.EndTitle -> endPoints.value = value
            MatchEnum.Ints.EndCapping -> {
                endCapping.value = value
                calculateEndPoints()
                calculateTotal()
            }
            MatchEnum.Ints.EndParked -> {
                endParked1.value = value
                calculateEndPoints()
                calculateTotal()
            }
            MatchEnum.Ints.EndParked1 -> {
                endParked1.value = value
                calculateEndPoints()
                calculateTotal()
            }
            MatchEnum.Ints.EndParked2 -> {
                endParked2.value = value
                calculateEndPoints()
                calculateTotal()
            }
            MatchEnum.Ints.TotalTitle -> totalPoints.value = value
        }
    }

    fun set(type: MatchEnum.Counters, value: Int) {
        when(type) {
            MatchEnum.Counters.AutoStorage -> {
                autoStorage.value = value
                calculateAutoPoints()
            }
            MatchEnum.Counters.AutoHubL1 -> {
                autoHub1.value = value
                calculateAutoPoints()
            }
            MatchEnum.Counters.AutoHubL2 -> {
                autoHub2.value = value
                calculateAutoPoints()
            }
            MatchEnum.Counters.AutoHubL3 -> {
                autoHub3.value = value
                calculateAutoPoints()
            }
            MatchEnum.Counters.DriverStorage -> {
                driverStorage.value = value
                calculateDriverPoints()
            }
            MatchEnum.Counters.DriverHub1 -> {
                driverHub1.value = value
                calculateDriverPoints()
            }
            MatchEnum.Counters.DriverHub2 -> {
                driverHub2.value = value
                calculateDriverPoints()
            }
            MatchEnum.Counters.DriverHub3 -> {
                driverHub3.value = value
                calculateDriverPoints()
            }
            MatchEnum.Counters.DriverShared -> {
                driverShared.value = value
                calculateDriverPoints()
            }
            MatchEnum.Counters.EndDucks -> {
                endDucks.value = value
                calculateEndPoints()
            }
        }
        calculateTotal()
    }

    fun add(type: MatchEnum.Counters, add: Int) {
        when(type) {
            MatchEnum.Counters.AutoStorage -> {
                autoStorage.value += add
                calculateAutoPoints()
            }
            MatchEnum.Counters.AutoHubL1 -> {
                autoHub1.value += add
                calculateAutoPoints()
            }
            MatchEnum.Counters.AutoHubL2 -> {
                autoHub2.value += add
                calculateAutoPoints()
            }
            MatchEnum.Counters.AutoHubL3 -> {
                autoHub3.value += add
                calculateAutoPoints()
            }
            MatchEnum.Counters.DriverStorage -> {
                driverStorage.value += add
                calculateDriverPoints()
            }
            MatchEnum.Counters.DriverHub1 -> {
                driverHub1.value += add
                calculateDriverPoints()
            }
            MatchEnum.Counters.DriverHub2 -> {
                driverHub2.value += add
                calculateDriverPoints()
            }
            MatchEnum.Counters.DriverHub3 -> {
                driverHub3.value += add
                calculateDriverPoints()
            }
            MatchEnum.Counters.DriverShared -> {
                driverShared.value += add
                calculateDriverPoints()
            }
            MatchEnum.Counters.EndDucks -> {
                endDucks.value += add
                calculateEndPoints()
            }
        }
        calculateTotal()
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
            MatchEnum.Ints.EndTitle -> endPoints
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

    private fun Boolean.toInt() = if (this) 1 else 0
}