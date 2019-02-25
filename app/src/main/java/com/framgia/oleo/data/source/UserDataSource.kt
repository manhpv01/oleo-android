package com.framgia.oleo.data.source

import com.framgia.oleo.data.source.model.Place
import com.framgia.oleo.data.source.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.ValueEventListener

interface UserDataSource {
    interface Local {
        fun getUser(): User?

        fun insertUser(vararg users: User)

        fun deleteUser()

        fun updateUser(vararg users: User)
    }

    interface Remote {
        fun pushUserLocation(id: String, place: Place)

        fun getUserByPhoneNumber(phoneNumber: String, valueEventListener: ValueEventListener)

        fun registerUser(
            user: User,
            onCompleteListener: OnCompleteListener<Void>,
            onFailureListener: OnFailureListener
        )

        fun getUsers(valueEventListener: ValueEventListener)

        fun followUser(
            user: User,
            onCompleteListener: OnCompleteListener<Void>,
            onFailureListener: OnFailureListener
        )

        fun unfollowUser(
            user: User,
            onCompleteListener: OnCompleteListener<Void>,
            onFailureListener: OnFailureListener
        )
    }
}
