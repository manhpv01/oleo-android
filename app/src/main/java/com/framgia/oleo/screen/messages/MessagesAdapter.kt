package com.framgia.oleo.screen.messages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.framgia.oleo.R
import com.framgia.oleo.data.source.model.RoomChat
import com.framgia.oleo.utils.Constant
import com.framgia.oleo.utils.OnItemRecyclerViewClick

class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.Companion.MessagesHolder>() {

    private var messages: MutableList<RoomChat> = arrayListOf()
    private lateinit var listener: OnItemRecyclerViewClick<RoomChat>

    fun setListener(itemClickListener: OnItemRecyclerViewClick<RoomChat>) {
        listener = itemClickListener
    }

    fun updateData(roomChat: RoomChat) {
        this.messages.add(roomChat)
        notifyItemInserted(this.messages.size - Constant.DEFAULT_ONE)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_message, parent, false)
        return MessagesHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: MessagesHolder, position: Int) {
        holder.bindData(messages[position])
    }

    companion object {
        class MessagesHolder(
            itemView: View,
            private val listener: OnItemRecyclerViewClick<RoomChat>
        ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            private lateinit var roomChat: RoomChat

            fun bindData(roomChat: RoomChat) {
                this.roomChat = roomChat
                itemView.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                listener.onItemClickListener(roomChat)
            }
        }
    }
}
