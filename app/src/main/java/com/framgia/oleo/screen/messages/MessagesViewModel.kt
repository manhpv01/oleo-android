package com.framgia.oleo.screen.messages

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import javax.inject.Inject

class MessagesViewModel @Inject constructor() : BaseViewModel() {

    companion object {
        fun create(fragment: Fragment, factory: ViewModelProvider.Factory): MessagesViewModel =
                ViewModelProvider(fragment, factory).get(MessagesViewModel::class.java)
    }
}
