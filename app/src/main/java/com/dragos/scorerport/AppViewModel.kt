package com.dragos.scorerport

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class AppViewModel: ViewModel() {
    var dynamicColorEnabled by mutableStateOf(false)

    val matchList = mutableStateListOf<MatchDisplay>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
        get() = statusMessage

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
            val matchDisplay = matchList.find{ it.key == dataSnapshot.key!! }
            matchList.remove(matchDisplay)
            matchDisplay?.set(
                newName = databaseResult.name,
                newTime = databaseResult.time,
                newPoints = databaseResult.points
            )
            if (matchDisplay != null) {
                matchList.add(matchDisplay)
            }
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
            statusMessage.value = Event("Failed to load matches.")
        }
    }

    private var database: DatabaseReference = Firebase.database.reference.child("matchList")

    init {
        database.addChildEventListener(childEventListener)
    }
}

open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    //fun peekContent(): T = content
}