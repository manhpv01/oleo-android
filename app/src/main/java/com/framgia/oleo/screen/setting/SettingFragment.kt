package com.framgia.oleo.screen.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.framgia.oleo.R
import com.framgia.oleo.base.BaseFragment
import com.framgia.oleo.databinding.FragmentSettingBinding
import com.framgia.oleo.utils.liveData.autoCleared

class SettingFragment : BaseFragment() {

    private lateinit var viewModel: SettingViewModel
    private var binding by autoCleared<FragmentSettingBinding>()

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = SettingViewModel.create(this, viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun setUpView() {
    }

    override fun bindView() {
    }

    companion object {
        fun newInstance() = SettingFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
