package com.framgia.oleo.screen.messages

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.framgia.oleo.base.BaseViewModel
import com.framgia.oleo.data.source.UserRepository
import com.framgia.oleo.data.source.model.User
import com.framgia.oleo.screen.signup.SignUpViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import javax.inject.Inject

class MessageOptionViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {
    private var isFollow = MutableLiveData<Boolean>()

    fun followUser(
        user: User,
        onCompleteListener: OnCompleteListener<Void>,
        onFailureListener: OnFailureListener
    ) {
        userRepository.followUser(user, onCompleteListener, onFailureListener)
    }

    fun unFollowUser(
        user: User,
        onCompleteListener: OnCompleteListener<Void>,
        onFailureListener: OnFailureListener
    ) {
        userRepository.unfollowUser(user, onCompleteListener, onFailureListener)
    }

    fun getIsFollow(): MutableLiveData<Boolean> {
        return isFollow
    }

    companion object {
        fun create(fragment: Fragment, factory: ViewModelProvider.Factory): MessageOptionViewModel =
            ViewModelProvider(fragment, factory).get(MessageOptionViewModel::class.java)
    }
}