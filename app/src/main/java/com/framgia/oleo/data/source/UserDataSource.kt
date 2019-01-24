package com.framgia.oleo.data.source

import com.framgia.oleo.data.source.model.User

interface UserDataSource {

    fun getUser(): User

    fun insertUser(vararg users: User)

    fun deleteUser()

    fun updateUser(vararg users: User)
}
