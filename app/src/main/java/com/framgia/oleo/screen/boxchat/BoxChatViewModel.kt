package com.framgia.oleo.screen.boxchat

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import com.framgia.oleo.data.source.UserRepository
import com.framgia.oleo.data.source.model.Message
import com.framgia.oleo.utils.Constant
import com.framgia.oleo.utils.Index
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*
import javax.inject.Inject

class BoxChatViewModel @Inject constructor(
    private val application: Application,
    private val userRepository: UserRepository,
    private val fireBaseDatabase: FirebaseDatabase,
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) : BaseViewModel() {

    private val user = userRepository.getUser()
    private lateinit var adapter: BoxChatAdapter
    private var oldMessageId = ""

    fun setAdapter(adapter: BoxChatAdapter) {
        this.adapter = adapter
        this.adapter.setUser(userRepository.getUser())
    }

    fun getAdapter() = adapter

    fun getMessage(roomId: String) {
        if (user != null) {
            fireBaseDatabase.reference.child(Constant.PATH_STRING_USER).child(user.id)
                .child(Constant.PATH_STRING_BOX).child(roomId).child(Constant.PATH_STRING_MESSAGE)
                .limitToLast(LIMIT_DATA)
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

                    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

                    override fun onChildRemoved(snapshot: DataSnapshot) {}

                    override fun onCancelled(snapshot: DatabaseError) {}

                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        val post = snapshot.getValue(Message::class.java)
                        adapter.updateData(post!!)
                        if (previousChildName == null) oldMessageId = snapshot.key.toString()
                    }
                })
        }
    }

    fun loadOldMessage(roomId: String) {
        if (user != null) {
            fireBaseDatabase.reference.child(Constant.PATH_STRING_USER).child(user.id)
                .child(Constant.PATH_STRING_BOX).child(roomId).child(Constant.PATH_STRING_MESSAGE)
                .limitToFirst(LIMIT_DATA).endAt(oldMessageId).orderByKey()
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEachIndexed { index, dataSnapshot ->
                            if (index == Index.POSITION_ONE) {
                                oldMessageId = dataSnapshot.key.toString()
                            }
                            val post = dataSnapshot.getValue(Message::class.java)
                            if (post != null) {
                                adapter.updateOldData(post)
                            }
                        }
                    }

                    override fun onCancelled(p0: DatabaseError) {}
                })
        }
    }

    fun sendMessage(text: String, boxId: String, userFriendId: String) {
        val messageId = fireBaseDatabase.reference.child(Constant.PATH_STRING_USER)
            .child(user.id)
            .child(Constant.PATH_STRING_BOX)
            .child(boxId)
            .child(Constant.PATH_STRING_MESSAGE)
            .push().key.toString()
        val message = Message(messageId, text, user.id, Calendar.getInstance().time.toString())
        saveMessage(user.id, boxId, messageId, message)
        saveMessage(userFriendId, boxId, messageId, message)
    }

    private fun saveMessage(userId: String, boxId: String, messageId: String, message: Message) {
        fireBaseDatabase.reference.child(Constant.PATH_STRING_USER)
            .child(userId)
            .child(Constant.PATH_STRING_BOX)
            .child(boxId)
            .child(Constant.PATH_STRING_MESSAGE)
            .child(messageId)
            .setValue(message)
    }

    companion object {

        const val LIMIT_DATA = 10

        fun create(fragment: Fragment, factory: ViewModelProvider.Factory): BoxChatViewModel =
            ViewModelProvider(fragment, factory).get(BoxChatViewModel::class.java)
    }
}
