package com.framgia.oleo.screen.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.framgia.oleo.R
import com.framgia.oleo.data.source.model.User
import com.framgia.oleo.databinding.AdapterSearchBindingImpl

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.Companion.ViewHolder>() {
    private var users: MutableList<User> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: AdapterSearchBindingImpl =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.adapter_search, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(users.get(position))
    }

    fun updateData(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    companion object {
        class ViewHolder(
            private val binding: AdapterSearchBindingImpl
        ) : RecyclerView.ViewHolder(binding.root) {

            fun bindData(user: User) {
                binding.user = user
            }
        }
    }
}
