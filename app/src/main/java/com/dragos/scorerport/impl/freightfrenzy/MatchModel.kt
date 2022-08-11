package com.dragos.scorerport.impl.freightfrenzy

data class MatchModel(
    val title: String = "",
    val alliance: Int = 0,
    val autoDuck: Boolean = false,
    val autoStorage: Int = 0,
    val autoHub1: Int = 0,
    val autoHub2: Int = 0,
    val autoHub3: Int = 0,
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
