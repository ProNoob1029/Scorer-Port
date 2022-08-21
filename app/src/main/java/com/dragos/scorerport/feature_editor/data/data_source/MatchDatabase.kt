package com.dragos.scorerport.feature_editor.data.data_source

import androidx.room.RoomDatabase
import com.dragos.scorerport.feature_editor.domain.model.Match

@androidx.room.Database(
    entities = [Match::class],
    version = 1
)
abstract class MatchDatabase: RoomDatabase() {

    abstract val matchDao: MatchDao

    companion object {
        const val DATABASE_NAME = "match_db"
    }

}