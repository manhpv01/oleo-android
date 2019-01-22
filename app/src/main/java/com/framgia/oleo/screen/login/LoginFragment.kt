package com.framgia.oleo.screen.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.framgia.oleo.R
import com.framgia.oleo.base.BaseFragment
import com.framgia.oleo.databinding.FragmentLoginBinding
import com.framgia.oleo.screen.home.HomeFragment
import com.framgia.oleo.utils.extension.replaceFragment
import com.framgia.oleo.utils.liveData.autoCleared
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.*

class LoginFragment : BaseFragment(), View.OnClickListener {

    private lateinit var viewModel: LoginViewModel
    private var binding by autoCleared<FragmentLoginBinding>()
    private lateinit var callBackManager: CallbackManager
    private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        FacebookSdk.sdkInitialize(activity!!.applicationContext)
        AppEventsLogger.activateApp(context)
        viewModel = LoginViewModel.create(this, viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun setUpView() {
        callBackManager = CallbackManager.Factory.create()
        binding.textViewLoginGG.setOnClickListener(this)
        initGoogle()
        textViewLoginFB.setOnClickListener(this)
    }

    override fun bindView() {
        signInWithFacebook()
        buttonLoginFB.fragment = this
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.textViewLoginFB -> {
                buttonLoginFB.performClick()
            }
            R.id.textViewLoginGG -> {
                signInWithGoogle()
            }
        }
    }

    private fun signInWithFacebook() {
        buttonLoginFB.setReadPermissions(Arrays.asList(PUBLIC_PROFILE, EMAIL))
        buttonLoginFB.registerCallback(callBackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                if (result != null) {
                    viewModel.receiveDataUserFacebook(result)
                    replaceFragment(R.id.containerMain, HomeFragment.newInstance())
                } else {
                    Toast.makeText(context, REQUEST_NULL, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException?) {
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callBackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_REQUEST) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.receiveDataUserGoogle(task)
            replaceFragment(R.id.containerMain, HomeFragment.newInstance())
        }
    }

    private fun initGoogle() {
        googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(activity!!, googleSignInOptions)
    }

    private fun signInWithGoogle() {
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
        const val REQUEST_NULL = "Data Null"
        const val PUBLIC_PROFILE = "public_profile"
        const val EMAIL = "email"

        fun newInstance() = LoginFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
