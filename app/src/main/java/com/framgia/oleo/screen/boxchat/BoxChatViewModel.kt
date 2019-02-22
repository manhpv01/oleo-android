package com.framgia.oleo.screen.boxchat

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import com.framgia.oleo.data.source.MessagesRepository
import com.framgia.oleo.data.source.UserRepository
import com.framgia.oleo.data.source.model.Message
import com.framgia.oleo.data.source.model.User
import com.framgia.oleo.utils.Index
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

class BoxChatViewModel @Inject constructor(
    private val application: Application,
    private val userRepository: UserRepository,
    private val messagesRepository: MessagesRepository
) : BaseViewModel() {

    private val user = userRepository.getUser()
    private lateinit var adapter: BoxChatAdapter
    private var oldMessageId = ""

    fun setAdapter(adapter: BoxChatAdapter) {
        this.adapter = adapter
        this.adapter.setUser(userRepository.getUser()!!)
    }

    fun getAdapter() = adapter

    fun getMessage(roomId: String) {
        messagesRepository.getMessage(user!!.id, roomId, object : ChildEventListener {
            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildRemoved(snapshot: DataSnapshot) {}

            override fun onCancelled(snapshot: DatabaseError) {}

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val post = snapshot.getValue(Message::class.java)
                if (post != null) adapter.updateData(post)
                if (previousChildName == null) oldMessageId = snapshot.key.toString()
            }
        })
    }

    fun loadOldMessage(roomId: String) {
        val message = arrayListOf<Message>()
        messagesRepository.getOldMessage(user!!.id, roomId, oldMessageId, object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEachIndexed { index, dataSnapshot ->
                    if (index == Index.POSITION_ZERO) oldMessageId = dataSnapshot.key.toString()
                    val post = dataSnapshot.getValue(Message::class.java)
                    if (post != null) message.add(post)
                }
                adapter.updateOldData(message)
            }

            override fun onCancelled(p0: DatabaseError) {}
        })
    }

    fun sendMessage(text: String, boxId: String, userFriendId: String) {
        val messageId = messagesRepository.getMessageId(user!!.id, boxId)
        val message = Message(messageId, user.id, text, DateFormat.getDateTimeInstance().format(Date()))
        saveMessage(user.id, boxId, messageId, message)
        saveMessage(userFriendId, boxId, messageId, message)
    }

    private fun saveMessage(userId: String, boxId: String, messageId: String, message: Message) {
        messagesRepository.sendMessage(userId, boxId, messageId, message)
    }

    fun getFriendImageProfile(userId: String) {
        messagesRepository.getImageProfile(userId, object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(dataSnapShot: DataSnapshot) {
                if (dataSnapShot.exists()) {
                    val user = dataSnapShot.getValue(User::class.java)
                    adapter.setUserFriendImage(user!!.image)
                }
            }
        })
    }

    companion object {

        const val LIMIT_DATA = 10

        fun create(fragment: Fragment, factory: ViewModelProvider.Factory): BoxChatViewModel =
            ViewModelProvider(fragment, factory).get(BoxChatViewModel::class.java)
    }
}
