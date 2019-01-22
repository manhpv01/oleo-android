package com.framgia.oleo.utils.extension

import android.content.Context
import com.framgia.oleo.R
import com.google.android.material.textfield.TextInputLayout


fun validInputPhoneNumber(context: Context, phoneNumber: String, textInputLayout: TextInputLayout): Boolean {
    if (phoneNumber.isEmpty()) {
        textInputLayout.error = context.getString(R.string.validPhoneNumber)
        return false
    }
    if (!phoneNumber.checkPhonePattern()) {
        textInputLayout.error = context.getString(R.string.validPhoneNumber)
        return false
    }
    textInputLayout.error = null
    return true
}

fun validInputPassword(context: Context, Password: String, textInputLayout: TextInputLayout): Boolean {
    if (Password.isEmpty()) {
        textInputLayout.error = context.getString(R.string.validPassword)
        return false
    }
    if (!Password.checkPasswordPattern()) {
        textInputLayout.error = context.getString(R.string.validPassword)
        return false
    }
    textInputLayout.error = null
    return true
}

fun String.checkPasswordPattern(): Boolean {
    this.let { return Regex(PASSWORD_PATTERN).find(this) != null }
}

fun String.checkMailPattern(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.checkPhonePattern(): Boolean {
    this.let { return Regex(PHONE_PATTERN).find(this) != null && this.length > 9 && this.length < 12 }
}

const val PASSWORD_PATTERN = "^(?=.*[A-Za-z])[A-Za-z0-9]{6,}$"
const val PHONE_PATTERN = "(09|01[2|3|4|5|6|7|8|9|0|1])([0-9]{8})\\b"
