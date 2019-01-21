package com.framgia.oleo.screen.login

import android.app.Application
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val application: Application) : BaseViewModel() {

    fun checkLastLogin(): Boolean {
        return GoogleSignIn.getLastSignedInAccount(application)!= null
    }

    fun signInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            //Xử lý lưu vào room database
        } catch (e: ApiException) {
            Toast.makeText(application, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        fun create(fragment: Fragment, factory: ViewModelProvider.Factory): LoginViewModel =
            ViewModelProvider(fragment, factory).get(LoginViewModel::class.java)
    }
}
