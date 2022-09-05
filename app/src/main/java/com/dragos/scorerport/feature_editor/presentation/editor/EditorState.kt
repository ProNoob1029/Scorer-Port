package com.dragos.scorerport.feature_editor.presentation.editor

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import com.dragos.scorerport.feature_editor.domain.model.Match
import com.dragos.scorerport.feature_editor.domain.model.MatchEnum

class NewState(
    match: Match = Match()
) {

    private val matchState = MatchState(match)

    fun get(type: MatchEnum.Strings): State<String> = matchState.get(type)
    fun get(type: MatchEnum.Ints): State<Int> = matchState.get(type)
    fun get(type: MatchEnum.Booleans): State<Boolean> = matchState.get(type)
    fun get(type: MatchEnum.Counters): State<Int> = matchState.get(type)

    fun set(type: MatchEnum.Strings, value: String) = matchState.set(type, value)
    fun set(type: MatchEnum.Booleans, value: Boolean) = matchState.set(type, value)
    fun set(type: MatchEnum.Ints, value: Int) = matchState.set(type, value)
    fun set(type: MatchEnum.Counters, add: Int) {
        when(type) {
            MatchEnum.Counters.AutoStorage -> {
                matchState.add(type, add)
                val type2 = MatchEnum.Counters.DriverStorage
                if (matchState.get(type2).value + add >= 0)
                    matchState.add(type2, add)
            }
            MatchEnum.Counters.AutoHubL1 -> {
                matchState.add(type, add)
                val type2 = MatchEnum.Counters.DriverHub1
                if (matchState.get(type2).value + add >= 0)
                    matchState.add(type2, add)
            }
            MatchEnum.Counters.AutoHubL2 -> {
                matchState.add(type, add)
                val type2 = MatchEnum.Counters.DriverHub2
                if (matchState.get(type2).value + add >= 0)
                    matchState.add(type2, add)
            }
            MatchEnum.Counters.AutoHubL3 -> {
                matchState.add(type, add)
                val type2 = MatchEnum.Counters.DriverHub3
                if (matchState.get(type2).value + add >= 0)
                    matchState.add(type2, add)
            }
            else -> matchState.add(type, add)
        }
    }

    fun getAnimatedVisibility(type: Any): State<Boolean> {
        return when(type) {
            MatchEnum.Booleans.AutoFullyParked -> matchState.autoFullyParkedVisible1
            MatchEnum.Booleans.AutoFullyParked1 -> matchState.autoFullyParkedVisible1
            MatchEnum.Booleans.AutoFullyParked2 -> matchState.autoFullyParkedVisible2
            else -> mutableStateOf(true)
        }
    }

    fun getVisibility(type: Any): State<Boolean> {
        return when(type) {
            MatchEnum.Ints.AutoFreightBonus -> matchState.team1
            MatchEnum.Ints.AutoFreightBonus1 -> matchState.team2
            MatchEnum.Ints.AutoFreightBonus2 -> matchState.team2
            MatchEnum.Ints.AutoParked -> matchState.team1
            MatchEnum.Ints.AutoParked1 -> matchState.team2
            MatchEnum.Ints.AutoParked2 -> matchState.team2
            MatchEnum.Booleans.AutoFullyParked -> matchState.team1
            MatchEnum.Booleans.AutoFullyParked1 -> matchState.team2
            MatchEnum.Booleans.AutoFullyParked2 -> matchState.team2
            MatchEnum.Ints.EndParked -> matchState.team1
            MatchEnum.Ints.EndParked1 -> matchState.team2
            MatchEnum.Ints.EndParked2 -> matchState.team2
            else -> mutableStateOf(true)
        }
    }

    fun getSpecialColor(type: Any): Boolean {
        return when(type) {
            MatchEnum.Ints.AutoFreightBonus2 -> true
            MatchEnum.Ints.AutoParked2 -> true
            MatchEnum.Booleans.AutoFullyParked2 -> true
            MatchEnum.Ints.EndParked2 -> true
            else -> false
        }
    }

    fun set(match: Match) = matchState.set(match)

    fun createMatch(match: Match) = matchState.createMatch(match)
}

private class MatchState(
    match: Match
) {
    val title = mutableStateOf(match.title)

    val team2 = mutableStateOf(match.team2)

    val alliance = mutableStateOf(match.alliance)
    val autoDuck = mutableStateOf(match.autoDuck)
    val autoStorage = mutableStateOf(match.autoStorage)
    val autoHub1 = mutableStateOf(match.autoHub1)
    val autoHub2 = mutableStateOf(match.autoHub2)
    val autoHub3 = mutableStateOf(match.autoHub3)
    val autoFreightBonus1 = mutableStateOf(match.autoFreightBonus1)
    val autoFreightBonus2 = mutableStateOf(match.autoFreightBonus2)
    val autoParked1 = mutableStateOf(match.autoParked1)
    val autoParked2 = mutableStateOf(match.autoParked2)
    val autoFullyParked1 = mutableStateOf(match.autoFullyParked1)
    val autoFullyParked2 = mutableStateOf(match.autoFullyParked2)
    val driverStorage = mutableStateOf(match.driverStorage)
    val driverHub1 = mutableStateOf(match.driverHub1)
    val driverHub2 = mutableStateOf(match.driverHub2)
    val driverHub3 = mutableStateOf(match.driverHub3)
    val driverShared = mutableStateOf(match.driverShared)
    val endBalanced = mutableStateOf(match.endBalanced)
    val endLeaning = mutableStateOf(match.endLeaning)
    val endDucks = mutableStateOf(match.endDucks)
    val endCapping = mutableStateOf(match.endCapping)
    val endParked1 = mutableStateOf(match.endParked1)
    val endParked2 = mutableStateOf(match.endParked2)

    val team1 = derivedStateOf { !team2.value }

    val autoFullyParkedVisible1 = derivedStateOf {
        autoParked1.value != 0
    }

    val autoFullyParkedVisible2 = derivedStateOf {
        autoParked2.value != 0
    }

    val autoPoints = derivedStateOf {
        autoDuck.value.toInt() * 10 +
        autoFreightBonus1.value * 10 +
        autoFreightBonus2.value * 10 * team2.value.toInt() +
        autoStorage.value * 2 +
        (autoHub1.value + autoHub2.value + autoHub3.value) * 6 +
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

    val driverPoints = derivedStateOf {
        driverStorage.value +
        driverHub1.value * 2 +
        driverHub2.value * 4 +
        driverHub3.value * 6 +
        driverShared.value * 4
    }

    val endPoints = derivedStateOf {
        endDucks.value * 6 +
        endBalanced.value.toInt() * 10 +
        endLeaning.value.toInt() * 20 +
        endCapping.value * 15 +
        endParked1.value * 3 +
        endParked2.value * 3 * team2.value.toInt()
    }

    val totalPoints = derivedStateOf {
        autoPoints.value + driverPoints.value + endPoints.value
    }

    fun set(match: Match) {
        title.value = match.title
        team2.value = match.team2
        alliance.value = match.alliance
        autoDuck.value = match.autoDuck
        autoStorage.value = match.autoStorage
        autoHub1.value = match.autoHub1
        autoHub2.value = match.autoHub2
        autoHub3.value = match.autoHub3
        autoFreightBonus1.value = match.autoFreightBonus1
        autoFreightBonus2.value = match.autoFreightBonus2
        autoParked1.value = match.autoParked1
        autoParked2.value = match.autoParked2
        autoFullyParked1.value = match.autoFullyParked1
        autoFullyParked2.value = match.autoFullyParked2
        driverStorage.value = match.driverStorage
        driverHub1.value = match.driverHub1
        driverHub2.value = match.driverHub2
        driverHub3.value = match.driverHub3
        driverShared.value = match.driverShared
        endBalanced.value = match.endBalanced
        endLeaning.value = match.endLeaning
        endDucks.value = match.endDucks
        endCapping.value = match.endCapping
        endParked1.value = match.endParked1
        endParked2.value = match.endParked2
    }

    fun createMatch(match: Match): Match {
        return  Match(
            key = match.key,
            version = match.version,

            title = title.value,
            createStamp = match.createStamp,
            editStamp = match.editStamp,
            totalPoints = totalPoints.value,

            team2 = team2.value,
            alliance = alliance.value,
            autoDuck = autoDuck.value,
            autoStorage = autoStorage.value,
            autoHub1 = autoHub1.value,
            autoHub2 = autoHub2.value,
            autoHub3 = autoHub3.value,
            autoFreightBonus1 = autoFreightBonus1.value,
            autoFreightBonus2 = autoFreightBonus2.value,
            autoParked1 = autoParked1.value,
            autoParked2 = autoParked2.value,
            autoFullyParked1 = autoFullyParked1.value,
            autoFullyParked2 = autoFullyParked2.value,
            driverStorage = driverStorage.value,
            driverHub1 = driverHub1.value,
            driverHub2 = driverHub2.value,
            driverHub3 = driverHub3.value,
            driverShared = driverShared.value,
            endBalanced = endBalanced.value,
            endLeaning = endLeaning.value,
            endDucks = endDucks.value,
            endCapping = endCapping.value,
            endParked1 = endParked1.value,
            endParked2 = endParked2.value,
        )
    }

    fun get(type: MatchEnum.Strings): State<String> {
        return when(type) {
            MatchEnum.Strings.TitleText -> title
        }
    }

    fun get(type: MatchEnum.Booleans): State<Boolean> {
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

    fun get(type: MatchEnum.Ints): State<Int> {
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

    fun get(type: MatchEnum.Counters): State<Int> {
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
            MatchEnum.Ints.AutoTitle -> Unit
            MatchEnum.Ints.AutoFreightBonus -> autoFreightBonus1.value = value
            MatchEnum.Ints.AutoFreightBonus1 -> autoFreightBonus1.value = value
            MatchEnum.Ints.AutoFreightBonus2 -> autoFreightBonus2.value = value
            MatchEnum.Ints.AutoParked -> autoParked1.value = value
            MatchEnum.Ints.AutoParked1 -> autoParked1.value = value
            MatchEnum.Ints.AutoParked2 -> autoParked2.value = value
            MatchEnum.Ints.DriverTitle -> Unit
            MatchEnum.Ints.EndTitle -> Unit
            MatchEnum.Ints.EndCapping -> endCapping.value = value
            MatchEnum.Ints.EndParked -> endParked1.value = value
            MatchEnum.Ints.EndParked1 -> endParked1.value = value
            MatchEnum.Ints.EndParked2 -> endParked2.value = value
            MatchEnum.Ints.TotalTitle -> Unit
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

    private fun Boolean.toInt() = if (this) 1 else 0
}