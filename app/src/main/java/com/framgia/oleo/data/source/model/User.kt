package com.framgia.oleo.data.source.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    lateinit var id: String

    @ColumnInfo(name = "user_name")
    lateinit var userName: String

    @ColumnInfo(name = "email")
    lateinit var email: String

    constructor()

    constructor(id: String, userName: String, email: String) {
        this.id = id
        this.userName = userName
        this.email = email
    }
}
