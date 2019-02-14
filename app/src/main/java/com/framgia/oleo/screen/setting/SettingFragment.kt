package com.framgia.oleo.screen.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.login.LoginManager
import com.framgia.oleo.R
import com.framgia.oleo.base.BaseFragment
import com.framgia.oleo.databinding.FragmentSettingBinding
import com.framgia.oleo.screen.login.LoginFragment
import com.framgia.oleo.utils.extension.isCheckMultiClick
import com.framgia.oleo.utils.extension.replaceFragment
import com.framgia.oleo.utils.liveData.autoCleared
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : BaseFragment(), View.OnClickListener {

    private lateinit var viewModel: SettingViewModel
    private var binding by autoCleared<FragmentSettingBinding>()

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = SettingViewModel.create(this, viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun setUpView() {
        textViewLogOut.setOnClickListener(this)
    }

    override fun bindView() {
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.textViewLogOut -> if (isCheckMultiClick()) logOut()
        }
    }

    private fun logOut() {
        val builder = AlertDialog.Builder(activity!!, R.style.alertDialog)
        builder.setMessage(getString(R.string.validate_log_out)).setTitle(getString(R.string.log_out))
        builder.setPositiveButton(getString(R.string.ok)) { dialog, id ->
            signOutFacebook()
            signOutGoogle()
            replaceFragment(R.id.containerMain, LoginFragment.newInstance(), false)
        }
        builder.setNegativeButton(getString(R.string.cancel)) { dialog, which -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun signOutFacebook() {
        if (AccessToken.getCurrentAccessToken() == null) {
            return //already logged out
        }
        GraphRequest(AccessToken.getCurrentAccessToken(),
                "/me/permissions/",
                null,
                HttpMethod.DELETE,
                GraphRequest.Callback { LoginManager.getInstance().logOut() }).executeAsync()
    }

    private fun signOutGoogle() {
        FirebaseAuth.getInstance()?.signOut()
    }

    companion object {
        fun newInstance() = SettingFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
