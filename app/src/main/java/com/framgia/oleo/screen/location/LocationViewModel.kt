package com.framgia.oleo.screen.location

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import com.framgia.oleo.data.source.model.Place
import javax.inject.Inject

class LocationViewModel @Inject constructor() : BaseViewModel() {
    private lateinit var locationAdapter: LocationAdapter

    fun setAdapter(locationAdapter: LocationAdapter) {
        this.locationAdapter = locationAdapter
    }

    fun getAdapter() = locationAdapter

    fun getListLocation() {
        // get list location from firebase database
        var places = arrayListOf<Place>()
        locationAdapter.updateData(places)
    }


    companion object {
        fun create(fragment: Fragment, factory: ViewModelProvider.Factory): LocationViewModel =
                ViewModelProvider(fragment, factory).get(LocationViewModel::class.java)
    }
}
