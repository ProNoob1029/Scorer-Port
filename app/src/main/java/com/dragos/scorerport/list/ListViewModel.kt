package com.dragos.scorerport.list

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragos.scorerport.DatabaseResult
import com.dragos.scorerport.MatchDisplay
import com.dragos.scorerport.newMatchDisplay
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {

    private val _sharedFlow = MutableSharedFlow<ScreenEvents>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    var dynamicColorEnabled by mutableStateOf(false)

    val matchList = mutableStateListOf<MatchDisplay>()

    private var database: DatabaseReference = Firebase.database.reference.child("test")

    private val childEventListener = object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
            //Log.d(TAG, "onChildAdded:" + dataSnapshot.key!!)

            // A new comment has been added, add it to the displayed list
            val databaseResult = dataSnapshot.getValue<DatabaseResult>() ?: return
            if(dataSnapshot.key == null) return
            val matchDisplay = newMatchDisplay(databaseResult, dataSnapshot.key!!)
            matchList.add(matchDisplay)
        }

        override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
            //Log.d(TAG, "onChildChanged: ${dataSnapshot.key}")

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so displayed the changed comment.
            val databaseResult = dataSnapshot.getValue<DatabaseResult>() ?: return
            if(dataSnapshot.key == null) return
            val key: String = dataSnapshot.key!!
            val index = matchList.indexOf( matchList.find{ it.key == key } )
            val matchDisplay: MatchDisplay = newMatchDisplay(databaseResult, key)
            matchList[index] = matchDisplay
        }

        override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            //Log.d(TAG, "onChildRemoved:" + dataSnapshot.key!!)

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so remove it.

            if(dataSnapshot.key == null) return
            matchList.remove(matchList.find { it.key == dataSnapshot.key!! })
        }

        override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
            //Log.d(TAG, "onChildMoved:" + dataSnapshot.key!!)

            // A comment has changed position, use the key to determine if we are
            // displaying this comment and if so move it.
        }

        override fun onCancelled(databaseError: DatabaseError) {
            //Log.w(TAG, "postComments:onCancelled", databaseError.toException())

            viewModelScope.launch {
                _sharedFlow.emit(
                    ScreenEvents.ShowToast(
                        "Failed to load matches.",
                        Toast.LENGTH_LONG
                    )
                )
            }
        }
    }

    init {
        database.orderByKey().addChildEventListener(childEventListener)
    }

    sealed class ScreenEvents {
        data class ShowToast(val message: String, val length: Int): ScreenEvents()
    }
}