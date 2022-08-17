package com.dragos.scorerport.feature_editor.presentation.editor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dragos.scorerport.impl.freightfrenzy.MatchEnum
import com.dragos.scorerport.impl.freightfrenzy.MatchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(

): ViewModel() {
    private val state = EditorState()

    fun get(type: MatchEnum.Strings): State<String> = state.get(type)
    fun get(type: MatchEnum.Ints): State<Int> = state.get(type)
    fun get(type: MatchEnum.Booleans): State<Boolean> = state.get(type)
    fun get(type: MatchEnum.Counters): State<Int> = state.get(type)

    fun set(type: MatchEnum.Strings, value: String) = state.set(type, value)
    fun set(type: MatchEnum.Booleans, value: Boolean) = state.set(type, value)
    fun set(type: MatchEnum.Ints, value: Int) {
        when(type) {
            MatchEnum.Ints.AutoParked -> {
                state.set(type, value)
                state.autoFullyParkedVisible1.value = value != 0
            }
            MatchEnum.Ints.AutoParked1 -> {
                state.set(type, value)
                state.autoFullyParkedVisible1.value = value != 0
            }
            MatchEnum.Ints.AutoParked2 -> {
                state.set(type, value)
                state.autoFullyParkedVisible2.value = value != 0
            }
            else -> state.set(type, value)
        }
    }
    fun set(type: MatchEnum.Counters, add: Int) {
        when(type) {
            MatchEnum.Counters.AutoStorage -> {
                state.add(type, add)
                val type2 = MatchEnum.Counters.DriverStorage
                if (state.get(type2).value + add >= 0)
                    state.add(type2, add)
            }
            MatchEnum.Counters.AutoHubL1 -> {
                state.add(type, add)
                val type2 = MatchEnum.Counters.DriverHub1
                if (state.get(type2).value + add >= 0)
                    state.add(type2, add)
            }
            MatchEnum.Counters.AutoHubL2 -> {
                state.add(type, add)
                val type2 = MatchEnum.Counters.DriverHub2
                if (state.get(type2).value + add >= 0)
                    state.add(type2, add)
            }
            MatchEnum.Counters.AutoHubL3 -> {
                state.add(type, add)
                val type2 = MatchEnum.Counters.DriverHub3
                if (state.get(type2).value + add >= 0)
                    state.add(type2, add)
            }
            else -> state.add(type, add)
        }
    }

    fun getAnimatedVisibility(type: Any): State<Boolean> {
        return when(type) {
            MatchEnum.Booleans.AutoFullyParked -> state.autoFullyParkedVisible1
            MatchEnum.Booleans.AutoFullyParked1 -> state.autoFullyParkedVisible1
            MatchEnum.Booleans.AutoFullyParked2 -> state.autoFullyParkedVisible2
            else -> mutableStateOf(true)
        }
    }

    fun getVisibility(type: Any): State<Boolean> {
        return when(type) {
            MatchEnum.Ints.AutoFreightBonus -> state.team1
            MatchEnum.Ints.AutoFreightBonus1 -> state.team2
            MatchEnum.Ints.AutoFreightBonus2 -> state.team2
            MatchEnum.Ints.AutoParked -> state.team1
            MatchEnum.Ints.AutoParked1 -> state.team2
            MatchEnum.Ints.AutoParked2 -> state.team2
            MatchEnum.Booleans.AutoFullyParked -> state.team1
            MatchEnum.Booleans.AutoFullyParked1 -> state.team2
            MatchEnum.Booleans.AutoFullyParked2 -> state.team2
            MatchEnum.Ints.EndParked -> state.team1
            MatchEnum.Ints.EndParked1 -> state.team2
            MatchEnum.Ints.EndParked2 -> state.team2
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

    fun reset() = state.set(MatchModel())
}