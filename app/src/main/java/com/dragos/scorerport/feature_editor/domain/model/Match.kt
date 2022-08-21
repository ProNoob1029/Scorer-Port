package com.dragos.scorerport.feature_editor.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dragos.scorerport.feature_editor.presentation.util.autoId

@Keep
@Entity
data class Match(
    @PrimaryKey val key: String = autoId(),
    val version: Int = 0,

    val title: String = "",
    val createStamp: Long = 0,
    val editStamp: Long = 0,
    val totalPoints: Int = 0,

    val team2: Boolean = false,
    val alliance: Int = 0,
    val autoDuck: Boolean = false,
    val autoStorage: Int = 0,
    val autoHub1: Int = 0,
    val autoHub2: Int = 0,
    val autoHub3: Int = 0,
    val autoFreightBonus1: Int = 0,
    val autoFreightBonus2: Int = 0,
    val autoParked1: Int = 0,
    val autoParked2: Int = 0,
    val autoFullyParked1: Boolean = false,
    val autoFullyParked2: Boolean = false,
    val driverStorage: Int = 0,
    val driverHub1: Int = 0,
    val driverHub2: Int = 0,
    val driverHub3: Int = 0,
    val driverShared: Int = 0,
    val endBalanced: Boolean = false,
    val endLeaning: Boolean = false,
    val endDucks: Int = 0,
    val endCapping: Int = 0,
    val endParked1: Int = 0,
    val endParked2: Int = 0
)
