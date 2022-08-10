package com.dragos.scorerport.feature_editor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.dragos.scorerport.feature_editor.domain.model.ItemEnum
import com.dragos.scorerport.feature_editor.domain.model.ItemState
import com.dragos.scorerport.feature_editor.presentation.edit.*

class MatchScreen: Screen {
    override val elements = listOf(
        Title("New Match", largeTitle = true),
        TextField(MatchEnum.Title, "Title"),
        AllianceButtons(MatchEnum.Alliance, "Red Alliance", "Blue Alliance"),
        Title("Autonomous: ", true),
        Switch(MatchEnum.AutoDuck, "Duck delivery: "),
        SegmentedButton(MatchEnum.AutoFreightBonus, "Feight Bonus", listOf("Duck", "Team\nelement")),
        Counter(MatchEnum.AutoStorage, "Freight in storage: "),
        Counter(MatchEnum.AutoHub, "Freight in hub: "),
        SegmentedButton(MatchEnum.AutoParked, "Parked in: ", listOf("Storage", "Warehouse")),
        Switch(MatchEnum.AutoFullyParked, "Fully parked: ")
    )
}

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

class MatchState(matchModel: MatchModel): ItemState {

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

    private val autoFullyParkedVisible = mutableStateOf(false)

    override fun getString(item: ItemEnum): State<String> {
        return when (item) {
            MatchEnum.Title -> title
            else -> mutableStateOf("")
        }
    }
    override fun getBoolean(item: ItemEnum): State<Boolean> {
        return when (item) {
            MatchEnum.AutoDuck -> autoDuck
            MatchEnum.AutoFullyParked -> autoFullyParked
            MatchEnum.EndBalanced -> endBalanced
            MatchEnum.EndLeaning -> endLeaning
            else -> mutableStateOf(false)
        }
    }
    override fun getInt(item: ItemEnum): State<Int> {
        return when (item) {
            MatchEnum.Alliance -> alliance
            MatchEnum.AutoStorage -> autoStorage
            MatchEnum.AutoHub -> autoHub
            MatchEnum.AutoFreightBonus -> autoFreightBonus
            MatchEnum.AutoParked -> autoParked
            MatchEnum.DriverStorage -> driverStorage
            MatchEnum.DriverHub1 -> driverHub1
            MatchEnum.DriverHub2 -> driverHub2
            MatchEnum.DriverHub3 -> driverHub3
            MatchEnum.DriverShared -> driverShared
            MatchEnum.EndDucks -> endDucks
            MatchEnum.EndParked -> endParked
            MatchEnum.EndCapping -> endCapping
            else -> mutableStateOf(0)
        }
    }

    override fun setString(item: ItemEnum, value: String) {
        when (item) {
            MatchEnum.Title -> title.value = value
            else -> mutableStateOf(value)
        }
    }
    override fun setBoolean(item: ItemEnum, value: Boolean) {
        when (item) {
            MatchEnum.AutoDuck -> autoDuck.value = value
            MatchEnum.AutoFullyParked -> autoFullyParked.value = value
            MatchEnum.EndBalanced -> endBalanced.value = value
            MatchEnum.EndLeaning -> endLeaning.value = value
            else -> mutableStateOf(value)
        }
    }
    override fun setInt(item: ItemEnum, value: Int) {
        when (item) {
            MatchEnum.Alliance -> alliance.value = value
            MatchEnum.AutoStorage -> autoStorage.value = value
            MatchEnum.AutoHub -> autoHub.value = value
            MatchEnum.AutoFreightBonus -> autoFreightBonus.value = value
            MatchEnum.AutoParked -> {
                autoParked.value = value
                if (value == 0) {
                    autoFullyParkedVisible.value = false
                    autoFullyParked.value = false
                } else {
                    autoFullyParkedVisible.value = true
                }
            }
            MatchEnum.DriverStorage -> driverStorage.value = value
            MatchEnum.DriverHub1 -> driverHub1.value = value
            MatchEnum.DriverHub2 -> driverHub2.value = value
            MatchEnum.DriverHub3 -> driverHub3.value = value
            MatchEnum.DriverShared -> driverShared.value = value
            MatchEnum.EndDucks -> endDucks.value = value
            MatchEnum.EndParked -> endParked.value = value
            MatchEnum.EndCapping -> endCapping.value = value
            else -> mutableStateOf(value)
        }
    }

    override fun getVisibility(item: ItemEnum): State<Boolean> {
        return when(item) {
            MatchEnum.AutoFullyParked -> autoFullyParkedVisible
            else -> mutableStateOf(true)
        }
    }
}

enum class MatchEnum: ItemEnum {
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