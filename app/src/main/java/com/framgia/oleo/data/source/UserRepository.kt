package com.framgia.oleo.data.source

import com.framgia.oleo.data.source.local.UserLocalDataSource
import com.framgia.oleo.data.source.model.User

class UserRepository(private val userLocalDataSource: UserLocalDataSource) : UserDataSource {

    override fun getUser(): User {
        return userLocalDataSource.getUser()
    }

    override fun insertUser(vararg users: User) {
        return userLocalDataSource.insertUser(*users)
    }

    override fun deleteUser() {
        return userLocalDataSource.deleteUser()
    }

    override fun updateUser(vararg users: User) {
        return userLocalDataSource.updateUser(*users)
    }
}
