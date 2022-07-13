package com.dragos.scorerport

import android.support.annotation.Keep
import com.google.firebase.database.IgnoreExtraProperties

data class MatchDisplay(var name: String, var time: String, var points: Int , var key: String){
    fun set(newName: String, newTime: String, newPoints: Int){
        name = newName
        time = newTime
        points = newPoints
    }
}

@IgnoreExtraProperties
@Keep
data class DatabaseResult(val name: String = "", val time: String = "", val points: Int =  0)

fun newMatchDisplay(result: DatabaseResult, key: String): MatchDisplay{
    return MatchDisplay(result.name, result.time, result.points, key)
}