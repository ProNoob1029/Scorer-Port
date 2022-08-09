package com.dragos.scorerport.feature_editor.domain.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

data class MatchModel(
    val title: String = "",
    val alliance: Int = 0,
    val autoDuck: Boolean = false,
    val autoStorage: Int = 0,
    val autoHub: Int = 0,
    val autoFreightBonus: Int = 0,
    val autoParked: Int = 0,
    val autoFullyParked: Boolean = false,
    val driverStorage: Int = 0,
    val driverHub1: Int = 0,
    val driverHub2: Int = 0,
    val driverHub3: Int = 0,
    val driverShared: Int = 0,
    val endDucks: Int = 0,
    val endBalanced: Boolean = false,
    val endLeaning: Boolean = false,
    val endParked: Int = 0,
    val endCapping: Int = 0
)

class MatchState(matchModel: MatchModel) {
    private val title = mutableStateOf(matchModel.title)
    private val alliance = mutableStateOf(matchModel.alliance)
    private val autoDuck = mutableStateOf(matchModel.autoDuck)
    private val autoStorage = mutableStateOf(matchModel.autoStorage)
    private val autoHub = mutableStateOf(matchModel.autoHub)
    private val autoFreightBonus = mutableStateOf(matchModel.autoFreightBonus)
    private val autoParked = mutableStateOf(matchModel.autoParked)
    private val autoFullyParked = mutableStateOf(matchModel.autoFullyParked)
    private val driverStorage = mutableStateOf(matchModel.driverStorage)
    private val driverHub1 = mutableStateOf(matchModel.driverHub1)
    private val driverHub2 = mutableStateOf(matchModel.driverHub2)
    private val driverHub3 = mutableStateOf(matchModel.driverHub3)
    private val driverShared = mutableStateOf(matchModel.driverShared)
    private val endDucks = mutableStateOf(matchModel.endDucks)
    private val endBalanced = mutableStateOf(matchModel.endBalanced)
    private val endLeaning = mutableStateOf(matchModel.endLeaning)
    private val endParked = mutableStateOf(matchModel.endParked)
    private val endCapping = mutableStateOf(matchModel.endCapping)

    /*val title: State<String> = _title
    val alliance: State<Int> = _alliance
    val autoDuck: State<Boolean> = _autoDuck
    val autoStorage: State<Int> = _autoStorage
    val autoHub: State<Int> = _autoHub
    val autoFreightBonus: State<Int> = _autoFreightBonus
    val autoParked: State<Int> = _autoParked
    val autoFullyParked: State<Boolean> = _autoFullyParked
    val driverStorage: State<Int> = _driverStorage
    val driverHub1: State<Int> = _driverHub1
    val driverHub2: State<Int> = _driverHub2
    val driverHub3: State<Int> = _driverHub3
    val driverShared: State<Int> = _driverShared
    val endDucks: State<Int> = _endDucks
    val endBalanced: State<Boolean> = _endBalanced
    val endLeaning: State<Boolean> = _endLeaning
    val endParked: State<Int> = _endParked
    val endCapping: State<Int> = _endCapping*/

    fun getString(match: Match): State<String> {
        return when (match) {
            Match.Title -> title
            else -> mutableStateOf("")
        }
    }
    fun getBoolean(match: Match): State<Boolean> {
        return when (match) {
            Match.AutoDuck -> autoDuck
            Match.AutoFullyParked -> autoFullyParked
            Match.EndBalanced -> endBalanced
            Match.EndLeaning -> endLeaning
            else -> mutableStateOf(false)
        }
    }
    fun getInt(match: Match): State<Int> {
        return when (match) {
            Match.Alliance -> alliance
            Match.AutoStorage -> autoStorage
            Match.AutoHub -> autoHub
            Match.AutoFreightBonus -> autoFreightBonus
            Match.AutoParked -> autoParked
            Match.DriverStorage -> driverStorage
            Match.DriverHub1 -> driverHub1
            Match.DriverHub2 -> driverHub2
            Match.DriverHub3 -> driverHub3
            Match.DriverShared -> driverShared
            Match.EndDucks -> endDucks
            Match.EndParked -> endParked
            Match.EndCapping -> endCapping
            else -> mutableStateOf(0)
        }
    }

    fun setString(match: Match, value: String) {
        when (match) {
            Match.Title -> title.value = value
            else -> mutableStateOf(value)
        }
    }
    fun setBoolean(match: Match, value: Boolean) {
        when (match) {
            Match.AutoDuck -> autoDuck.value = value
            Match.AutoFullyParked -> autoFullyParked.value = value
            Match.EndBalanced -> endBalanced.value = value
            Match.EndLeaning -> endLeaning.value = value
            else -> mutableStateOf(value)
        }
    }
    fun setInt(match: Match, value: Int) {
        when (match) {
            Match.Alliance -> alliance.value = value
            Match.AutoStorage -> autoStorage.value = value
            Match.AutoHub -> autoHub.value = value
            Match.AutoFreightBonus -> autoFreightBonus.value = value
            Match.AutoParked -> autoParked.value = value
            Match.DriverStorage -> driverStorage.value = value
            Match.DriverHub1 -> driverHub1.value = value
            Match.DriverHub2 -> driverHub2.value = value
            Match.DriverHub3 -> driverHub3.value = value
            Match.DriverShared -> driverShared.value = value
            Match.EndDucks -> endDucks.value = value
            Match.EndParked -> endParked.value = value
            Match.EndCapping -> endCapping.value = value
            else -> mutableStateOf(value)
        }
    }
}

enum class Match {
    Title,
    Alliance,
    AutoDuck,
    AutoStorage,
    AutoHub,
    AutoFreightBonus,
    AutoParked,
    AutoFullyParked,
    DriverStorage,
    DriverHub1,
    DriverHub2,
    DriverHub3,
    DriverShared,
    EndDucks,
    EndBalanced,
    EndLeaning,
    EndParked,
    EndCapping,
}