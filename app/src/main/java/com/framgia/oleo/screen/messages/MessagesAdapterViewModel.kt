package com.framgia.oleo.screen.messages

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.framgia.oleo.data.source.model.Message
import com.framgia.oleo.data.source.model.User
import com.framgia.oleo.utils.Constant
import com.google.firebase.database.*

class MessagesAdapterViewModel : BaseObservable() {

    private val fireBaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var liveMessage: MutableLiveData<Message> = MutableLiveData()
    private var user: MutableLiveData<User> = MutableLiveData()

    fun setMessage(userId: String, roomId: String) {
        fireBaseDatabase.reference.child(Constant.PATH_STRING_USER).child(userId)
            .child(Constant.PATH_STRING_BOX).child(roomId).child(Constant.PATH_STRING_MESSAGE)
            .addChildEventListener(object : ChildEventListener {

                override fun onCancelled(databaseError: DatabaseError) {}

                override fun onChildMoved(dataSnapshot: DataSnapshot, p1: String?) {}

                override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {}

                override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                    liveMessage.value = dataSnapshot.getValue(Message::class.java)
                }

                override fun onChildRemoved(p0: DataSnapshot) {}
            })
    }

    fun setUser(userId: String) {
        fireBaseDatabase.reference.child(Constant.PATH_STRING_USER).child(userId)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(databaseError: DatabaseError) {}

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    user.value = dataSnapshot.getValue(User::class.java)
                }
            })
    }

    fun getMessage() = liveMessage.value

    fun getUser() = user.value
}
