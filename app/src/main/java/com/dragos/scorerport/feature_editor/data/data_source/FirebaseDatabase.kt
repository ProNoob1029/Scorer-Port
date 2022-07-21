package com.dragos.scorerport.feature_editor.data.data_source

import android.widget.Toast
import com.dragos.scorerport.ScorerApp
import com.dragos.scorerport.feature_editor.domain.model.ListItemModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirebaseDatabase (
    private val scorerAppContext: ScorerApp,
) {
    private val list = mutableListOf<ListItemModel>()

    private val listFLow = MutableStateFlow(listOf<ListItemModel>())
    private val listState = listFLow.asStateFlow()

    private var database: DatabaseReference = Firebase.database.reference

    private var lastLocation: String? = null

    private val childEventListener = object : ChildEventListener {
        override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
            //Log.d(TAG, "onChildAdded:" + dataSnapshot.key!!)

            // A new comment has been added, add it to the displayed list
            val databaseResult = dataSnapshot.getValue<ListItemModel>() ?: return
            val listItem = ListItemModel(
                title = databaseResult.title,
                timeStamp = databaseResult.timeStamp,
                points = databaseResult.points,
                key = dataSnapshot.key!!
            )
            list.add(listItem)
            listFLow.value = list.toList()
        }

        override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
            //Log.d(TAG, "onChildChanged: ${dataSnapshot.key}")

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so displayed the changed comment.
            val databaseResult = dataSnapshot.getValue<ListItemModel>() ?: return
            val listItem = ListItemModel(
                title = databaseResult.title,
                timeStamp = databaseResult.timeStamp,
                points = databaseResult.points,
                key = dataSnapshot.key!!
            )
            val index = list.indexOf( list.find{ it.key == listItem.key } )
            list[index] = listItem
            listFLow.value = list.toList()
        }

        override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            //Log.d(TAG, "onChildRemoved:" + dataSnapshot.key!!)

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so remove it.

            if(dataSnapshot.key == null) return
            list.remove(list.find { it.key == dataSnapshot.key!! })
            listFLow.value = list.toList()
        }

        override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
            //Log.d(TAG, "onChildMoved:" + dataSnapshot.key!!)

            // A comment has changed position, use the key to determine if we are
            // displaying this comment and if so move it.
        }

        override fun onCancelled(databaseError: DatabaseError) {
            //Log.w(TAG, "postComments:onCancelled", databaseError.toException())

            Toast.makeText(scorerAppContext , databaseError.toString(), Toast.LENGTH_LONG).show()
        }
    }

    fun changeLocation(location: String) {
        if(lastLocation != null){
            if(location == lastLocation) return
            database.child(lastLocation!!).removeEventListener(childEventListener)
            list.clear()
            listFLow.value = list.toList()
        }
        database.child(location).addChildEventListener(childEventListener)
        lastLocation = location
    }

    fun getList() : StateFlow<List<ListItemModel>> {
        return listState
    }
}