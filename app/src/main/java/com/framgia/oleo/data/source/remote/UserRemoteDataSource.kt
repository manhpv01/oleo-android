package com.framgia.oleo.data.source.remote

import com.framgia.oleo.data.source.UserDataSource
import com.framgia.oleo.data.source.model.Place
import com.framgia.oleo.data.source.model.User
import com.framgia.oleo.utils.Constant
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserRemoteDataSource : UserDataSource.Remote {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    override fun pushUserLocation(id: String, place: Place) {
        firebaseDatabase.getReference(Constant.PATH_STRING_USER)
            .child(id)
            .child(Constant.PATH_STRING_PLACE)
            .child(id)
            .setValue(place)
    }

    override fun getUserByPhoneNumber(phoneNumber: String, valueEventListener: ValueEventListener) {
        firebaseDatabase.getReference(Constant.PATH_STRING_USER)
            .child(phoneNumber)
            .addValueEventListener(valueEventListener)
    }

    override fun registerUser(
        user: User,
        onCompleteListener: OnCompleteListener<Void>,
        onFailureListener: OnFailureListener
    ) {
        firebaseDatabase.getReference(Constant.PATH_STRING_USER)
            .child(user.phoneNumber)
            .setValue(user)
            .addOnCompleteListener(onCompleteListener)
            .addOnFailureListener(onFailureListener)
    }
}
