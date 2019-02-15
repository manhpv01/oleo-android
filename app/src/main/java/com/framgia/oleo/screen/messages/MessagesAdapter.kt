package com.framgia.oleo.screen.messages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.framgia.oleo.R
import com.framgia.oleo.data.source.model.BoxChat
import com.framgia.oleo.data.source.model.User
import com.framgia.oleo.databinding.AdapterMessageBinding
import com.framgia.oleo.utils.Constant
import com.framgia.oleo.utils.OnItemRecyclerViewClick

class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.Companion.MessagesHolder>() {

    private var messages: MutableList<BoxChat> = arrayListOf()
    private lateinit var listener: OnItemRecyclerViewClick<BoxChat>
    private lateinit var user: User

    fun setListener(itemClickListener: OnItemRecyclerViewClick<BoxChat>) {
        listener = itemClickListener
    }

    fun updateData(boxChat: BoxChat) {
        messages.add(boxChat)
        notifyItemInserted(this.messages.size - Constant.DEFAULT_ONE)
    }

    fun getUser(user: User) {
        this.user = user
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesHolder {
        val binding: AdapterMessageBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.adapter_message, parent, false)
        return MessagesHolder(binding, listener, user)
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessagesHolder, position: Int) {
        holder.bindData(messages[position])
    }

    companion object {
        class MessagesHolder(
            private val binding: AdapterMessageBinding,
            private val listener: OnItemRecyclerViewClick<BoxChat>,
            private val user: User
        ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

            init {
                binding.viewModel = MessagesAdapterViewModel()
                binding.root.setOnClickListener(this)
            }

            private lateinit var boxChat: BoxChat

            fun bindData(boxChat: BoxChat) {
                this.boxChat = boxChat
                binding.textFriendName.text = boxChat.userFriendName
                binding.viewModel!!.setMessage(user.id, boxChat.id!!)
                binding.viewModel!!.setUser(boxChat.userFriendId!!)
            }

            override fun onClick(v: View?) {
                listener.onItemClickListener(boxChat)
            }
        }
    }
}
