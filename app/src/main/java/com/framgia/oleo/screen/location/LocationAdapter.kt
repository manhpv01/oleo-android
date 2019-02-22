package com.framgia.oleo.screen.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.framgia.oleo.R
import com.framgia.oleo.data.source.model.Place
import com.framgia.oleo.databinding.AdapterLocationBinding

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.Companion.LocationHolder>() {
    private var places: MutableList<Place> = arrayListOf()

    fun updateData(places: MutableList<Place>) {
        this.places.addAll(places)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder {
        val binding: AdapterLocationBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.adapter_location, parent, false)
        return LocationHolder(binding)
    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onBindViewHolder(holder: LocationHolder, position: Int) {
        holder.bindData(places[position])
    }

    companion object {
        class LocationHolder(private val binding: AdapterLocationBinding) : RecyclerView.ViewHolder(binding.root) {

            init {
                binding.viewModel = LocationAdapterViewModel()
            }

            fun bindData(place: Place) {
                binding.viewModel!!.setLocation(place)
            }
        }
    }
}
