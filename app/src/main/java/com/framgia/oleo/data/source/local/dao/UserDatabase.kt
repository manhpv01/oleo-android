package com.framgia.oleo.data.source.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.framgia.oleo.data.source.local.dao.UserDatabase.Companion.DATABASE_VERSION
import com.framgia.oleo.data.source.model.User

@Database(entities = [User::class], version = DATABASE_VERSION, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Room-database"
    }
}
