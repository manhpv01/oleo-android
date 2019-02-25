package com.framgia.oleo.screen.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.framgia.oleo.R
import com.framgia.oleo.R.string
import com.framgia.oleo.databinding.FragmentOptionMessageBinding
import com.framgia.oleo.base.BaseFragment
import com.framgia.oleo.data.source.model.User
import com.framgia.oleo.utils.di.Injectable
import com.framgia.oleo.utils.extension.showSnackBar
import com.framgia.oleo.utils.extension.showToast
import com.framgia.oleo.utils.liveData.autoCleared
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import kotlinx.android.synthetic.main.toolbar.imageToolbar
import javax.inject.Inject

class MessageOptionFragment : BaseFragment(), Injectable, View.OnClickListener {
    @Inject
    internal lateinit var viewModel: MessageOptionViewModel
    private var binding by autoCleared<FragmentOptionMessageBinding>()

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_option_message, null, false)
        return binding.root
    }

    override fun setUpView() {
        viewModel = MessageOptionViewModel.create(this, viewModelFactory)
        binding.viewModel = viewModel
        imageToolbar.setOnClickListener { fragmentManager?.popBackStack() }
        binding.textViewFollow.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (viewModel.getIsFollow().value) {
            true -> unFollowUser()
            false -> followUser()
        }
    }

    override fun bindView() {
        viewModel.getIsFollow().observe(this, Observer {
            if (it) {
                binding.textViewFollow.text = (getString(R.string.unfollow))
                binding.textViewFollow.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.ic_unfollow,
                    0,
                    0,
                    0
                )
            } else {
                binding.textViewFollow.text = (getString(R.string.follow))
                binding.textViewFollow.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.ic_follow_user,
                    0,
                    0,
                    0
                )
            }
        })
        viewModel.getIsFollow().value = true
    }

    private fun unFollowUser() {
        viewModel.getIsFollow().value = false
        viewModel.unFollowUser(User(),
            OnCompleteListener {
                if (!it.isSuccessful) return@OnCompleteListener
                view?.showSnackBar(getString(string.message_unfollow_success))
            },
            OnFailureListener {
                activity?.showToast(getString(string.message_unfollow_fail))
            })
    }

    private fun followUser() {
        viewModel.getIsFollow().value = true
        viewModel.followUser(
            User(),
            OnCompleteListener {
                if (!it.isSuccessful) return@OnCompleteListener
                view?.showSnackBar(getString(string.message_follow_success))
            },
            OnFailureListener {
                view?.showSnackBar(getString(string.message_follow_fail))
            })
    }

    companion object {
        fun newInstance() = MessageOptionFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
