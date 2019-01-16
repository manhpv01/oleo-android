package com.framgia.oleo.screen.main

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    companion object {
        fun create(
            activity: FragmentActivity,
            factory: ViewModelProvider.Factory
        ): MainViewModel = ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }
}
