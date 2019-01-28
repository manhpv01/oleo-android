package com.framgia.oleo.screen.messages

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import com.framgia.oleo.data.source.UserRepository
import com.framgia.oleo.data.source.model.RoomChat
import com.framgia.oleo.utils.Constant
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class MessagesViewModel @Inject constructor(
    private val application: Application,
    private val userRepository: UserRepository,
    private val fireBaseDatabase: FirebaseDatabase
) : BaseViewModel() {

    private lateinit var messageAdapter: MessagesAdapter

    fun setAdapter(messageAdapter: MessagesAdapter) {
        this.messageAdapter = messageAdapter
    }

    fun getAllMessages() {
        val user = userRepository.getUser()
        val databaseReference =
            fireBaseDatabase.reference.child(Constant.PATH_STRING_USER).child(user.id).child(Constant.PATH_STRING_ROOM)
        databaseReference.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {}

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val data = dataSnapshot.getValue(RoomChat::class.java)
                messageAdapter.updateData(data!!)
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
        })
    }

    companion object {
        fun create(fragment: Fragment, factory: ViewModelProvider.Factory): MessagesViewModel =
            ViewModelProvider(fragment, factory).get(MessagesViewModel::class.java)
    }
}
