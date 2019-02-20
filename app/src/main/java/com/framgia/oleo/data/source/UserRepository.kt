package com.framgia.oleo.data.source

import com.framgia.oleo.data.source.model.Place
import com.framgia.oleo.data.source.model.User

class UserRepository(
    private val local: UserDataSource.Local,
    private val remote: UserDataSource.Remote
) : UserDataSource.Local, UserDataSource.Remote {
    override fun pushUserLocation(id: String, place: Place) {
        remote.pushUserLocation(id, place)
    }

    override fun getUser(): User {
        return local.getUser()
    }

    override fun insertUser(vararg users: User) {
        return local.insertUser(*users)
    }

    override fun deleteUser() {
        return local.deleteUser()
    }

    override fun updateUser(vararg users: User) {
        return local.updateUser(*users)
    }
}
