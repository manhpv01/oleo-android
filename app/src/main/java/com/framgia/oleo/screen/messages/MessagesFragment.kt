package com.framgia.oleo.screen.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.framgia.oleo.R
import com.framgia.oleo.base.BaseFragment
import com.framgia.oleo.data.source.model.BoxChat
import com.framgia.oleo.databinding.FragmentMessagesBinding
import com.framgia.oleo.screen.boxchat.BoxChatFragment
import com.framgia.oleo.utils.OnItemRecyclerViewClick
import com.framgia.oleo.utils.extension.addFragment
import com.framgia.oleo.utils.liveData.autoCleared
import kotlinx.android.synthetic.main.fragment_messages.*

class MessagesFragment : BaseFragment(), OnItemRecyclerViewClick<BoxChat>, SearchView.OnQueryTextListener, View.OnClickListener {

    private lateinit var viewModel: MessagesViewModel
    private var binding by autoCleared<FragmentMessagesBinding>()
    private var messagesAdapter = MessagesAdapter()

    override fun createView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View {
        viewModel = MessagesViewModel.create(this, viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_messages, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun setUpView() {
        // SetUp View
        recyclerViewMessages.adapter = messagesAdapter
        viewModel.setAdapter(messagesAdapter)
        messagesAdapter.setListener(this)
        setUpSearchActionBar()
    }

    override fun bindView() {
        // Add Show View
        viewModel.getAllMessages()
    }

    override fun onItemClickListener(data: BoxChat) {
        //Open Chat Screen
        addFragment(R.id.containerMain, BoxChatFragment.newInstance(data))
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.imageNewMessage -> {
                // Add New Message
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        // Add Suggest Result
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // Add Suggest Result
        return true
    }

    private fun setUpSearchActionBar() {
        searchMessage.setIconifiedByDefault(false)
        searchMessage.setOnQueryTextListener(this)
        val searchEditText = searchMessage.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        searchEditText.setTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.colorDefault))
        searchEditText.setHintTextColor(ContextCompat.getColor(activity!!.applicationContext, R.color.colorGrey500))
    }

    companion object {
        fun newInstance() = MessagesFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
