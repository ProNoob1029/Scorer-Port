package com.dragos.scorerport.feature_editor.presentation.editor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.dragos.scorerport.impl.freightfrenzy.MatchEnum
import com.dragos.scorerport.impl.freightfrenzy.MatchModel

class EditorState(
    match: MatchModel = MatchModel()
) {
    val title = mutableStateOf(match.title)
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
    val endDucks = mutableStateOf(match.endDucks)
    val endBalanced = mutableStateOf(match.endBalanced)
    val endLeaning = mutableStateOf(match.endLeaning)
    val endParked1 = mutableStateOf(match.endParked1)
    val endParked2 = mutableStateOf(match.endParked2)
    val endCapping = mutableStateOf(match.endCapping)

    val team2 = mutableStateOf(match.team2)
    val team1 = mutableStateOf(!match.team2)

    val autoPoints = mutableStateOf(0)
    val driverPoints = mutableStateOf(0)
    val endPoints = mutableStateOf(0)
    val totalPoints = mutableStateOf(0)


    fun set(type: MatchEnum.Strings, value: String) {
        when(type) {
            MatchEnum.Strings.TitleText -> title.value = value
        }
    }

    fun set(type: MatchEnum.Booleans, value: Boolean) {
        when(type) {
            MatchEnum.Booleans.AutoDuck -> autoDuck.value = value
            else -> Unit
        }
    }

    fun set(type: MatchEnum.Ints, value: Int) {
        when(type) {
            MatchEnum.Ints.Alliance -> alliance.value = value
            else -> Unit
        }
    }

    fun set(type: MatchEnum.Counters, add: Int) {
        when(type) {
            MatchEnum.Counters.AutoStorage -> autoStorage.value += add
            else -> Unit
        }
    }

    fun get(type: MatchEnum.Strings): State<String> {
        return when(type) {
            MatchEnum.Strings.TitleText -> title
        }
    }

    fun get(type: MatchEnum.Booleans): State<Boolean> {
        return when(type) {
            MatchEnum.Booleans.AutoDuck -> autoDuck
            else -> mutableStateOf(false)
        }
    }

    fun get(type: MatchEnum.Ints): State<Int> {
        return when(type) {
            MatchEnum.Ints.Alliance -> alliance
            MatchEnum.Ints.AutoTitle -> autoPoints
            else -> mutableStateOf(0)
        }
    }

    fun get(type: MatchEnum.Counters): State<Int> {
        return when(type) {
            MatchEnum.Counters.AutoStorage -> autoStorage
            else -> mutableStateOf(0)
        }
    }
}