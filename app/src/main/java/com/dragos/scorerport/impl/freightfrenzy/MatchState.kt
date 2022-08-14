package com.dragos.scorerport.impl.freightfrenzy

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.dragos.scorerport.impl.models.ItemEnum
import com.dragos.scorerport.impl.models.ItemModel
import com.dragos.scorerport.impl.models.ItemState

class MatchState(matchModel: MatchModel = MatchModel()): ItemState {

    private val title = mutableStateOf(matchModel.title)
    private val alliance = mutableStateOf(matchModel.alliance)
    private val autoDuck = mutableStateOf(matchModel.autoDuck)
    private val autoStorage = mutableStateOf(matchModel.autoStorage)
    private val autoHub1 = mutableStateOf(matchModel.autoHub1)
    private val autoHub2 = mutableStateOf(matchModel.autoHub2)
    private val autoHub3 = mutableStateOf(matchModel.autoHub3)
    private val autoFreightBonus1 = mutableStateOf(matchModel.autoFreightBonus1)
    private val autoFreightBonus2 = mutableStateOf(matchModel.autoFreightBonus2)
    private val autoParked1 = mutableStateOf(matchModel.autoParked1)
    private val autoParked2 = mutableStateOf(matchModel.autoParked2)
    private val autoFullyParked1 = mutableStateOf(matchModel.autoFullyParked1)
    private val autoFullyParked2 = mutableStateOf(matchModel.autoFullyParked2)
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

    private val team2 = mutableStateOf(matchModel.team2)
    private val team1 = mutableStateOf(!matchModel.team2)

    private val autoPoints = mutableStateOf(0)
    private val driverPoints = mutableStateOf(0)
    private val endPoints = mutableStateOf(0)
    private val totalPoints = mutableStateOf(0)

    init {
        calculatePoints()
    }

    private val autoFullyParked1Visible = mutableStateOf(false)
    private val autoFullyParked2Visible = mutableStateOf(false)

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
                autoFreightBonus2.value * 10 +
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
                } * (autoFullyParked2.value.toInt() + 1)
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
            endParked.value * 3
    }
    private fun calculateTotal() {
        totalPoints.value = autoPoints.value + driverPoints.value + endPoints.value
    }

    override fun get(item: ItemEnum.Strings): State<String> {
        return when (item) {
            MatchEnum.Strings.TitleText -> title
            else -> mutableStateOf("")
        }
    }

    override fun get(item: ItemEnum.Booleans): State<Boolean> {
        return when (item) {
            MatchEnum.Booleans.AutoDuck -> autoDuck
            MatchEnum.Booleans.AutoFullyParked -> autoFullyParked1
            MatchEnum.Booleans.AutoFullyParked1 -> autoFullyParked1
            MatchEnum.Booleans.AutoFullyParked2 -> autoFullyParked2
            MatchEnum.Booleans.EndBalanced -> endBalanced
            MatchEnum.Booleans.EndLeaning -> endLeaning
            MatchEnum.Booleans.Title -> team2
            else -> mutableStateOf(false)
        }
    }

    override fun get(item: ItemEnum.Ints): State<Int> {
        return when (item) {
            MatchEnum.Ints.Alliance -> alliance
            MatchEnum.Ints.AutoFreightBonus -> autoFreightBonus1
            MatchEnum.Ints.AutoFreightBonus1 -> autoFreightBonus1
            MatchEnum.Ints.AutoFreightBonus2 -> autoFreightBonus2
            MatchEnum.Ints.AutoParked -> autoParked1
            MatchEnum.Ints.AutoParked1 -> autoParked1
            MatchEnum.Ints.AutoParked2 -> autoParked2
            MatchEnum.Ints.EndParked -> endParked
            MatchEnum.Ints.EndCapping -> endCapping
            MatchEnum.Ints.AutoTitle -> autoPoints
            MatchEnum.Ints.DriverTitle -> driverPoints
            MatchEnum.Ints.EndTitle -> endPoints
            MatchEnum.Ints.TotalTitle -> totalPoints
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
            MatchEnum.Strings.TitleText -> title.value = value
            else -> mutableStateOf(value)
        }
    }

    override fun set(item: ItemEnum.Booleans, value: Boolean) {
        when (item) {
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
            MatchEnum.Booleans.Title -> {
                team2.value = value
                team1.value = !value
                if (!value) {
                    autoFreightBonus2.value = 0
                    autoParked2.value = 0
                    autoFullyParked2.value = false
                    autoFullyParked2Visible.value = false
                }
            }
            else -> mutableStateOf(value)
        }
        calculateTotal()
    }

    override fun set(item: ItemEnum.Ints, value: Int) {
        when (item) {
            MatchEnum.Ints.Alliance -> alliance.value = value
            MatchEnum.Ints.AutoFreightBonus -> {
                autoFreightBonus1.value = value
                calculateAutoPoints()
            }
            MatchEnum.Ints.AutoFreightBonus1 -> {
                autoFreightBonus1.value = value
                calculateAutoPoints()
            }
            MatchEnum.Ints.AutoFreightBonus2 -> {
                autoFreightBonus2.value = value
                calculateAutoPoints()
            }
            MatchEnum.Ints.AutoParked -> {
                autoParked1.value = value
                if (value == 0) {
                    autoFullyParked1Visible.value = false
                    autoFullyParked1.value = false
                } else {
                    autoFullyParked1Visible.value = true
                }
                calculateAutoPoints()
            }
            MatchEnum.Ints.AutoParked1 -> {
                autoParked1.value = value
                if (value == 0) {
                    autoFullyParked1Visible.value = false
                    autoFullyParked1.value = false
                } else {
                    autoFullyParked1Visible.value = true
                }
                calculateAutoPoints()
            }
            MatchEnum.Ints.AutoParked2 -> {
                autoParked2.value = value
                if (value == 0) {
                    autoFullyParked2Visible.value = false
                    autoFullyParked2.value = false
                } else {
                    autoFullyParked2Visible.value = true
                }
                calculateAutoPoints()
            }
            MatchEnum.Ints.EndParked -> {
                endParked.value = value
                calculateEndPoints()
            }
            MatchEnum.Ints.EndCapping -> {
                endCapping.value = value
                calculateEndPoints()
            }
            else -> mutableStateOf(value)
        }
        calculateTotal()
    }

    override fun set(item: ItemEnum.Counters, add: Int) {
        when (item) {
            MatchEnum.Counters.AutoStorage -> {
                autoStorage.value += add
                if (driverStorage.value + add >= 0) {
                    driverStorage.value += add
                    calculateDriverPoints()
                }
                calculateAutoPoints()
            }
            MatchEnum.Counters.AutoHubL1 -> {
                autoHub1.value += add
                if(driverHub1.value + add >= 0) {
                    driverHub1.value += add
                    calculateDriverPoints()
                }
                calculateAutoPoints()
            }
            MatchEnum.Counters.AutoHubL2 -> {
                autoHub2.value += add
                if(driverHub2.value + add >= 0) {
                    driverHub2.value += add
                    calculateDriverPoints()
                }
                calculateAutoPoints()
            }
            MatchEnum.Counters.AutoHubL3 ->
            {
                autoHub3.value += add
                if(driverHub3.value + add >= 0) {
                    driverHub3.value += add
                    calculateDriverPoints()
                }
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
            else -> mutableStateOf(add)
        }
        calculateTotal()
    }

    override fun set(item: ItemModel) {
        if (item is MatchModel) {
            title.value = item.title
            alliance.value = item.alliance
            autoDuck.value = item.autoDuck
            autoStorage.value = item.autoStorage
            autoHub1.value = item.autoHub1
            autoHub2.value = item.autoHub2
            autoHub3.value = item.autoHub3
            autoFreightBonus1.value = item.autoFreightBonus1
            autoParked1.value = item.autoParked1
            autoFullyParked1.value = item.autoFullyParked1
            driverStorage.value = item.driverStorage
            driverHub1.value = item.driverHub1
            driverHub2.value = item.driverHub2
            driverHub3.value = item.driverHub3
            driverShared.value = item.driverShared
            endDucks.value = item.endDucks
            endBalanced.value = item.endBalanced
            endLeaning.value = item.endLeaning
            endParked.value = item.endParked
            endCapping.value = item.endCapping
            team2.value = item.team2
            team1.value = !item.team2
            calculatePoints()
        }
    }

    override fun getVisibility(item: ItemEnum): State<Boolean> {
        return when(item) {
            MatchEnum.Booleans.AutoFullyParked -> team1
            MatchEnum.Booleans.AutoFullyParked1 -> team2
            MatchEnum.Booleans.AutoFullyParked2 -> team2
            MatchEnum.Ints.AutoFreightBonus -> team1
            MatchEnum.Ints.AutoFreightBonus1 -> team2
            MatchEnum.Ints.AutoFreightBonus2 -> team2
            MatchEnum.Ints.AutoParked -> team1
            MatchEnum.Ints.AutoParked1 -> team2
            MatchEnum.Ints.AutoParked2 -> team2
            else -> mutableStateOf(true)
        }
    }

    override fun getAnimatedVisibility(item: ItemEnum): State<Boolean> {
        return when(item) {
            MatchEnum.Booleans.AutoFullyParked -> autoFullyParked1Visible
            MatchEnum.Booleans.AutoFullyParked1 -> autoFullyParked1Visible
            MatchEnum.Booleans.AutoFullyParked2 -> autoFullyParked2Visible
            else -> mutableStateOf(true)
        }
    }

    override fun specialColor(item: ItemEnum): Boolean {
        return when(item) {
            MatchEnum.Ints.AutoFreightBonus2 -> true
            MatchEnum.Ints.AutoParked2 -> true
            MatchEnum.Booleans.AutoFullyParked2 -> true
            else -> false
        }
    }

    private fun Boolean.toInt() = if (this) 1 else 0
}
