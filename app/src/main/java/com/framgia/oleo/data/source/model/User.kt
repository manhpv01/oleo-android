package com.framgia.oleo.data.source.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String = ""

    @ColumnInfo(name = "user_name")
    var userName: String = ""

    @ColumnInfo(name = "email")
    var email: String = ""

    @ColumnInfo(name = "phone_number")
    var phoneNumber: String = ""

    constructor()

    constructor(id: String, userName: String, email: String) {
        this.id = id
        this.userName = userName
        this.email = email
    }
}
