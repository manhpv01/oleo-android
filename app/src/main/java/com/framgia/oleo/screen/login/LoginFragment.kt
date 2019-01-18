package com.framgia.oleo.screen.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.framgia.oleo.R
import com.framgia.oleo.base.BaseFragment
import com.framgia.oleo.databinding.FragmentLoginBinding
import com.framgia.oleo.screen.home.HomeFragment
import com.framgia.oleo.utils.extension.replaceFragment
import com.framgia.oleo.utils.liveData.autoCleared
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class LoginFragment : BaseFragment(), View.OnClickListener {

    private lateinit var viewModel: LoginViewModel
    private var binding by autoCleared<FragmentLoginBinding>()

    private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = LoginViewModel.create(this, viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun setUpView() {
        binding.textViewLoginGG.setOnClickListener(this)
        initGoogle()
    }

    override fun bindView() {
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.textViewLoginGG -> {
                signIn()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_REQUEST) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.signInResult(task)
            replaceFragment(R.id.containerMain, HomeFragment.newInstance())
        }
    }

    private fun initGoogle() {
        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(activity!!, googleSignInOptions)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, LoginFragment.GOOGLE_REQUEST)
    }

    private fun revokeAccess() {
        googleSignInClient.revokeAccess().addOnCompleteListener(activity!!) { task ->
            if (task.isSuccessful) {
            }
        }
    }

    companion object {
        const val GOOGLE_REQUEST = 1

        fun newInstance() = LoginFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
