package com.framgia.oleo.screen.messages

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import com.framgia.oleo.data.source.UserRepository
import com.framgia.oleo.data.source.model.BoxChat
import com.framgia.oleo.utils.Constant
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class MessagesViewModel @Inject constructor(
    private val application: Application,
    private val userRepository: UserRepository,
    private val fireBaseDatabase: FirebaseDatabase,
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) : BaseViewModel() {

    private lateinit var messageAdapter: MessagesAdapter

    fun setAdapter(messageAdapter: MessagesAdapter) {
        this.messageAdapter = messageAdapter
        if (userRepository.getUser() != null) {
            this.messageAdapter.getUser(userRepository.getUser())
        }
    }

    fun getAllMessages() {
        val user = userRepository.getUser()
        if (user != null) {
            val databaseReference =
                fireBaseDatabase.reference.child(Constant.PATH_STRING_USER).child(user.id)
                    .child(Constant.PATH_STRING_BOX)
            databaseReference.addChildEventListener(object : ChildEventListener {
                override fun onCancelled(dataSnapshot: DatabaseError) {}

                override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {}

                override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {}

                override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                    val data = dataSnapshot.getValue(BoxChat::class.java)
                    messageAdapter.updateData(data!!)
                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            })
        }
    }

    companion object {
        fun create(fragment: Fragment, factory: ViewModelProvider.Factory): MessagesViewModel =
            ViewModelProvider(fragment, factory).get(MessagesViewModel::class.java)
    }
}

