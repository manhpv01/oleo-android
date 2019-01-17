package com.framgia.oleo.screen.login

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    companion object {
        fun create(fragment: Fragment, factory: ViewModelProvider.Factory): LoginViewModel =
            ViewModelProvider(fragment, factory).get(LoginViewModel::class.java)
    }
}
