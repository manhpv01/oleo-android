package com.framgia.oleo.data.source.model

class Message() {

    private var message: String? = null
    private var user: String? = null
    private var time: String? = null

    constructor(user: String, text: String, time: String) : this() {
        this.user = user
        this.message = text
        this.time = time
    }
}
