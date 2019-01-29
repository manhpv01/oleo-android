package com.framgia.oleo.screen.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.framgia.oleo.R
import com.framgia.oleo.base.BaseFragment
import com.framgia.oleo.data.source.model.RoomChat
import com.framgia.oleo.databinding.FragmentMessagesBinding
import com.framgia.oleo.utils.OnItemRecyclerViewClick
import com.framgia.oleo.utils.liveData.autoCleared

class MessagesFragment : BaseFragment(), OnItemRecyclerViewClick<RoomChat> {

    private lateinit var viewModel: MessagesViewModel
    private var binding by autoCleared<FragmentMessagesBinding>()
    private var messagesAdapter = MessagesAdapter()

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = MessagesViewModel.create(this, viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_messages, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun setUpView() {
        // SetUp View
        viewModel.setAdapter(messagesAdapter)
        messagesAdapter.setListener(this)
    }

    override fun bindView() {
        // Add Show View
//        viewModel.getAllMessages()
    }

    override fun onItemClickListener(data: RoomChat) {
        //Open Chat Screen
    }

    companion object {
        fun newInstance() = MessagesFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
