package com.framgia.oleo.screen.messages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.framgia.oleo.R
import com.framgia.oleo.base.BaseFragment
import com.framgia.oleo.screen.main.MainActivity
import kotlinx.android.synthetic.main.toolbar.imageToolbar
import kotlinx.android.synthetic.main.toolbar.toolbarCustom

class MessageOptionFragment : BaseFragment() {
    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_option_message, container, false)
    }

    override fun setUpView() {
        imageToolbar.setOnClickListener { fragmentManager?.popBackStack() }
    }

    override fun bindView() {
    }

    companion object {
        fun newInstance() = MessageOptionFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
