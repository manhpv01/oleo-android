package com.framgia.oleo.screen.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.framgia.oleo.R
import com.framgia.oleo.base.BaseFragment
import com.framgia.oleo.databinding.FragmentLocationBinding
import com.framgia.oleo.utils.liveData.autoCleared
import kotlinx.android.synthetic.main.fragment_location.*

class LocationFragment : BaseFragment() {
    private lateinit var viewModel: LocationViewModel
    private var binding by autoCleared<FragmentLocationBinding>()
    private var locationAdapter = LocationAdapter()

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = LocationViewModel.create(this, viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_location, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun setUpView() {
        viewModel.setAdapter(locationAdapter)
        locationToolbar.setNavigationIcon(R.drawable.ic_back)
    }

    override fun bindView() {
        viewModel.getListLocation()
    }

    companion object {
        fun newInstance() = LocationFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
