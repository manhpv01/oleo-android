package com.framgia.oleo.screen.boxchat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.framgia.oleo.R
import com.framgia.oleo.data.source.model.Message
import com.framgia.oleo.data.source.model.User
import com.framgia.oleo.utils.Index

class BoxChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var messages: ArrayList<Message> = ArrayList()
    private lateinit var user: User

    fun updateData(message: Message) {
        messages.add(Index.POSITION_ZERO, message)
        notifyItemChanged(messages.lastIndex)
    }

    fun updateOldData(message: Message) {
        if (messages[Index.POSITION_ZERO].id != message.id) {
            messages.add(Index.POSITION_ZERO, message)
            notifyItemInserted(Index.POSITION_ZERO)
        }
    }

    fun setUser(user: User) {
        this.user = user
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_MESSAGE_SEND) MessageSendHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.adapter_message_send, viewGroup, false
            )
        ) else MessageReceiveHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.adapter_message_receive, viewGroup, false
            )
        )
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        when (holder) {
            is MessageSendHolder -> holder.bindData(messages[pos])
            is MessageReceiveHolder -> holder.bindData(messages[pos])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].userId!! == user.id) VIEW_TYPE_MESSAGE_SEND
        else VIEW_TYPE_MESSAGE_RECEIVE
    }

    class MessageSendHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(message: Message) {}
    }

    class MessageReceiveHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(message: Message) {}
    }

    companion object {
        const val VIEW_TYPE_MESSAGE_SEND: Int = 0
        const val VIEW_TYPE_MESSAGE_RECEIVE: Int = 1
    }
}
