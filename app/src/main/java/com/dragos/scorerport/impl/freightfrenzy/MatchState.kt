package com.dragos.scorerport.impl.freightfrenzy

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.dragos.scorerport.impl.models.ItemEnum
import com.dragos.scorerport.impl.models.ItemState

class MatchState(matchModel: MatchModel): ItemState {

    private val title = mutableStateOf(matchModel.title)
    private val alliance = mutableStateOf(matchModel.alliance)
    private val autoDuck = mutableStateOf(matchModel.autoDuck)
    private val autoStorage = mutableStateOf(matchModel.autoStorage)
    private val autoHub1 = mutableStateOf(matchModel.autoHub1)
    private val autoHub2 = mutableStateOf(matchModel.autoHub2)
    private val autoHub3 = mutableStateOf(matchModel.autoHub3)
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

    private val autoPoints = mutableStateOf(0)
    private val driverPoints = mutableStateOf(0)

    init {
        calculateAutoPoints()
        calculateDriverPoints()
    }

    private val autoFullyParkedVisible = mutableStateOf(false)

    private fun calculateAutoPoints() {
        val autoHub = autoHub1.value + autoHub2.value + autoHub3.value
        autoPoints.value =
        autoDuck.value.toInt() * 10 +
                autoFreightBonus.value * 10 +
                autoStorage.value * 2 + autoHub * 6 +
                when(autoParked.value) {
                    1 -> 3
                    2 -> 5
                    else -> 0
                } * (autoFullyParked.value.toInt() + 1)
    }
    private fun calculateDriverPoints() {
        driverPoints.value =
            driverStorage.value +
            driverHub1.value * 2 +
            driverHub2.value * 4 +
            driverHub3.value * 6 +
            driverShared.value * 4
    }

    override fun get(item: ItemEnum.Strings): State<String> {
        return when (item) {
            MatchEnum.Strings.Title -> title
            else -> mutableStateOf("")
        }
    }

    override fun get(item: ItemEnum.Booleans): State<Boolean> {
        return when (item) {
            MatchEnum.Booleans.AutoDuck -> autoDuck
            MatchEnum.Booleans.AutoFullyParked -> autoFullyParked
            MatchEnum.Booleans.EndBalanced -> endBalanced
            MatchEnum.Booleans.EndLeaning -> endLeaning
            else -> mutableStateOf(false)
        }
    }

    override fun get(item: ItemEnum.Ints): State<Int> {
        return when (item) {
            MatchEnum.Ints.Alliance -> alliance
            MatchEnum.Ints.AutoFreightBonus -> autoFreightBonus
            MatchEnum.Ints.AutoParked -> autoParked
            MatchEnum.Ints.EndParked -> endParked
            MatchEnum.Ints.EndCapping -> endCapping
            MatchEnum.Ints.AutoTitle -> autoPoints
            MatchEnum.Ints.DriverTitle -> driverPoints
            else -> mutableStateOf(0)
        }
    }

    override fun get(item: ItemEnum.Counters): State<Int> {
        return when (item) {
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
            else -> mutableStateOf(0)
        }
    }

    override fun set(item: ItemEnum.Strings, value: String) {
        when (item) {
            MatchEnum.Strings.Title -> title.value = value
            else -> mutableStateOf(value)
        }
    }

    override fun set(item: ItemEnum.Booleans, value: Boolean) {
        when (item) {
            MatchEnum.Booleans.AutoDuck -> autoDuck.value = value
            MatchEnum.Booleans.AutoFullyParked -> autoFullyParked.value = value
            MatchEnum.Booleans.EndBalanced -> endBalanced.value = value
            MatchEnum.Booleans.EndLeaning -> endLeaning.value = value
            else -> mutableStateOf(value)
        }
        calculateAutoPoints()
    }

    override fun set(item: ItemEnum.Ints, value: Int) {
        when (item) {
            MatchEnum.Ints.Alliance -> alliance.value = value
            MatchEnum.Ints.AutoFreightBonus -> autoFreightBonus.value = value
            MatchEnum.Ints.AutoParked -> {
                autoParked.value = value
                if (value == 0) {
                    autoFullyParkedVisible.value = false
                    autoFullyParked.value = false
                } else {
                    autoFullyParkedVisible.value = true
                }
            }
            MatchEnum.Ints.EndParked -> endParked.value = value
            MatchEnum.Ints.EndCapping -> endCapping.value = value
            else -> mutableStateOf(value)
        }
        calculateAutoPoints()
    }

    override fun set(item: ItemEnum.Counters, add: Int) {
        when (item) {
            MatchEnum.Counters.AutoStorage -> autoStorage.value += add
            MatchEnum.Counters.AutoHubL1 -> {
                autoHub1.value += add
                if(driverHub1.value + add >= 0)
                    driverHub1.value += add
            }
            MatchEnum.Counters.AutoHubL2 -> {
                autoHub2.value += add
                if(driverHub2.value + add >= 0)
                    driverHub2.value += add
            }
            MatchEnum.Counters.AutoHubL3 ->
            {
                autoHub3.value += add
                if(driverHub3.value + add >= 0)
                    driverHub3.value += add
            }
            MatchEnum.Counters.DriverStorage -> driverStorage.value += add
            MatchEnum.Counters.DriverHub1 -> driverHub1.value += add
            MatchEnum.Counters.DriverHub2 -> driverHub2.value += add
            MatchEnum.Counters.DriverHub3 -> driverHub3.value += add
            MatchEnum.Counters.DriverShared -> driverShared.value += add
            MatchEnum.Counters.EndDucks -> endDucks.value += add
            else -> mutableStateOf(add)
        }
        calculateAutoPoints()
        calculateDriverPoints()
    }

    override fun getVisibility(item: ItemEnum): State<Boolean> {
        return when(item) {
            MatchEnum.Booleans.AutoFullyParked -> autoFullyParkedVisible
            else -> mutableStateOf(true)
        }
    }

    private fun Boolean.toInt() = if (this) 1 else 0
}