package com.framgia.oleo.screen.setting

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import javax.inject.Inject

class SettingViewModel @Inject constructor() : BaseViewModel() {

    companion object {
        fun create(fragment: Fragment, factory: ViewModelProvider.Factory): SettingViewModel =
            ViewModelProvider(fragment, factory).get(SettingViewModel::class.java)
    }
}
