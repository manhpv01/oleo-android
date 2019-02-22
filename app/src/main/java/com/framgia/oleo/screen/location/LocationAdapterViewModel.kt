package com.framgia.oleo.screen.location

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.framgia.oleo.data.source.model.Place

class LocationAdapterViewModel : BaseObservable() {
    private var location: MutableLiveData<Place> = MutableLiveData()

    fun setLocation(place: Place) {
        location.value = place
    }

    fun getLocation() = location.value
}
