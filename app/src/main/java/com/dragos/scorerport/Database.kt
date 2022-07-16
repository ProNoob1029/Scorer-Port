package com.dragos.scorerport

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class Database (
    private val appContext: Application
) {
    var matchList = mutableStateListOf<MatchDisplay>()

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

            Toast.makeText(appContext , databaseError.toString(), Toast.LENGTH_LONG).show()
        }
    }

    init {
        database.orderByKey().addChildEventListener(childEventListener)
    }
}