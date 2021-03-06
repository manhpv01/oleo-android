package com.framgia.oleo.screen.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.framgia.oleo.R
import com.framgia.oleo.base.BaseFragment
import com.framgia.oleo.databinding.FragmentMessagesBinding
import com.framgia.oleo.utils.liveData.autoCleared

class MessagesFragment : BaseFragment() {

    private lateinit var viewModel: MessagesViewModel
    private var binding by autoCleared<FragmentMessagesBinding>()

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = MessagesViewModel.create(this, viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_messages, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun setUpView() {
    }

    override fun bindView() {
    }

    companion object {
        fun newInstance() = MessagesFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
