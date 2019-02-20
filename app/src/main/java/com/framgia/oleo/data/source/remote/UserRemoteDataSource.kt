package com.framgia.oleo.data.source.remote

import com.framgia.oleo.data.source.UserDataSource
import com.framgia.oleo.data.source.model.Place
import com.framgia.oleo.utils.Constant
import com.google.firebase.database.FirebaseDatabase

class UserRemoteDataSource : UserDataSource.Remote {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    override fun pushUserLocation(id: String, place: Place) {
        firebaseDatabase.getReference(Constant.PATH_STRING_USER)
            .child(id)
            .child(Constant.PATH_STRING_PLACE)
            .child(id)
            .setValue(place)
    }
}
