package com.framgia.oleo.data.source

import com.framgia.oleo.data.source.model.Place
import com.framgia.oleo.data.source.model.User

interface UserDataSource {
    interface Local {
        fun getUser(): User

        fun insertUser(vararg users: User)

        fun deleteUser()

        fun updateUser(vararg users: User)
    }

    interface Remote {
        fun pushUserLocation(id: String, place: Place)
    }
}
