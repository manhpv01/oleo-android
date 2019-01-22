package com.framgia.oleo.screen.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.framgia.oleo.R
import com.framgia.oleo.base.BaseFragment
import com.framgia.oleo.databinding.FragmentLoginBinding
import com.framgia.oleo.screen.home.HomeFragment
import com.framgia.oleo.utils.extension.replaceFragment
import com.framgia.oleo.utils.extension.showSnackBar
import com.framgia.oleo.utils.extension.validInputPassword
import com.framgia.oleo.utils.extension.validInputPhoneNumber
import com.framgia.oleo.utils.liveData.autoCleared
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.api.ApiException
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.*


class LoginFragment : BaseFragment(), View.OnClickListener {

    private lateinit var viewModel: LoginViewModel
    private var binding by autoCleared<FragmentLoginBinding>()
    private lateinit var callBackManager: CallbackManager
    private lateinit var googleSignInOptions: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private var validPhone = false
    private var validPassword = false

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = LoginViewModel.create(this, viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun setUpView() {
        callBackManager = CallbackManager.Factory.create()
        addEditTextListener()
        initGoogle()
        textLayoutPassWord.isPasswordVisibilityToggleEnabled = true

        buttonLogin.setOnClickListener(this)
        textViewLoginFB.setOnClickListener(this)
        textViewLoginGG.setOnClickListener(this)
    }

    override fun bindView() {
        signInWithFacebook()
        buttonLoginFB.fragment = this
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.textViewLoginFB -> buttonLoginFB.performClick()
            R.id.textViewLoginGG -> signInWithGoogle()
            R.id.buttonLogin -> if (validPassword && validPhone) replaceFragment(
                R.id.containerMain,
                HomeFragment.newInstance()
            )
        }
    }

    private fun addEditTextListener() {
        editTextPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validPhone = validInputPhoneNumber(activity!!, s.toString(), textLayoutPhoneNumber)
                if (s.isNullOrBlank()) textLayoutPhoneNumber.error = null
            }
        })
        editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validPassword = validInputPassword(activity!!, s.toString(), textLayoutPassWord)
                if (s.isNullOrBlank()) textLayoutPassWord.error = null
            }
        })
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
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                viewModel.receiveDataUserGoogle(task)
                if (task.exception == null) replaceFragment(R.id.containerMain, HomeFragment.newInstance())
            } catch (e: ApiException) {
                when (e.statusCode) {
                    GoogleSignInStatusCodes.SIGN_IN_CANCELLED -> view!!.showSnackBar(GOOGLE_SIGN_CANCELLED)
                    GoogleSignInStatusCodes.SIGN_IN_FAILED -> view!!.showSnackBar(GOOGLE_SIGN_FAILED)
                }
            }
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
        const val GOOGLE_SIGN_CANCELLED = "Sign Cancelled"
        const val GOOGLE_SIGN_FAILED = "Sign Failed"

        fun newInstance() = LoginFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}
