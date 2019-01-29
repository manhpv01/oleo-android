package com.framgia.oleo.screen.signup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import javax.inject.Inject

class SignUpViewModel @Inject constructor() : BaseViewModel() {

    companion object {
        fun create(fragment: Fragment, factory: ViewModelProvider.Factory): SignUpViewModel =
            ViewModelProvider(fragment, factory).get(SignUpViewModel::class.java)
    }
}
