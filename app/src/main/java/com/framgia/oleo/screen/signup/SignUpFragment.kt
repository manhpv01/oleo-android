package com.framgia.oleo.screen.signup

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.R
import com.framgia.oleo.databinding.FragmentSignupBinding
import com.framgia.oleo.utils.di.Injectable
import com.framgia.oleo.utils.liveData.autoCleared
import javax.inject.Inject

class SignUpFragment : DialogFragment(), Injectable {

    @Inject
    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SignUpViewModel
    private var binding by autoCleared<FragmentSignupBinding>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        return null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater: LayoutInflater = activity!!.layoutInflater
        viewModel = SignUpViewModel.create(this, viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, null, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        val dialog = AlertDialog.Builder(activity)
                .setView(binding.root)
                .create()
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    companion object {
        fun newInstance() = SignUpFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
