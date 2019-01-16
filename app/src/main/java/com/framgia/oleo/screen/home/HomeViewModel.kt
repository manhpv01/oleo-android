package com.framgia.oleo.screen.home

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel() {

    companion object {
        fun create(fragment: Fragment, factory: ViewModelProvider.Factory): HomeViewModel =
            ViewModelProvider(fragment, factory).get(HomeViewModel::class.java)
    }
}
