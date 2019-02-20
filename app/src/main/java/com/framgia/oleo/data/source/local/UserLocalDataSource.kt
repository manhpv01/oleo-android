package com.framgia.oleo.data.source.local

import com.framgia.oleo.data.source.UserDataSource
import com.framgia.oleo.data.source.local.dao.UserDAO
import com.framgia.oleo.data.source.model.User

class UserLocalDataSource(private val userDAO: UserDAO) : UserDataSource.Local {

    override fun getUser(): User {
        return userDAO.getUser
    }

    override fun insertUser(vararg users: User) {
        userDAO.insertUser(*users)
    }

    override fun deleteUser() {
        userDAO.deleteUser()
    }

    override fun updateUser(vararg users: User) {
        userDAO.updateUser(*users)
    }
}
