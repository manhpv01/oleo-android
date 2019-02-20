package com.framgia.oleo.screen.signup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import com.framgia.oleo.data.source.UserRepository
import com.framgia.oleo.data.source.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {
    fun getUserByPhoneNumber(phoneNumber: String, valueEventListener: ValueEventListener) {
        userRepository.getUserByPhoneNumber(phoneNumber, valueEventListener)
    }

    fun registerUser(
        user: User,
        onCompleteListener: OnCompleteListener<Void>,
        onFailureListener: OnFailureListener
    ) {
        userRepository.registerUser(user, onCompleteListener, onFailureListener)
    }

    companion object {
        fun create(fragment: Fragment, factory: ViewModelProvider.Factory): SignUpViewModel =
            ViewModelProvider(fragment, factory).get(SignUpViewModel::class.java)
    }
}
