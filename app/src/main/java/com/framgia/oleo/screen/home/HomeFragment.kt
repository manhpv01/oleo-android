package com.framgia.oleo.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.framgia.oleo.R
import com.framgia.oleo.base.BaseFragment
import com.framgia.oleo.databinding.FragmentHomeBinding
import com.framgia.oleo.utils.liveData.autoCleared

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    private var binding by autoCleared<FragmentHomeBinding>()

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = HomeViewModel.create(this, viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun setUpView() {
        // SetUp View
    }

    override fun bindView() {
        // Add Show View
    }

    companion object {
        fun newInstance() = HomeFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
