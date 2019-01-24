package com.framgia.oleo.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.framgia.oleo.data.source.model.User

@Dao
interface UserDAO {
    @get:Query("SELECT * FROM users")
    val getUser: User

    @Insert
    fun insertUser(vararg users: User)

    @Query("DELETE FROM users")
    fun deleteUser()

    @Update
    fun updateUser(vararg users: User)
}
