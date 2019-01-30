package com.framgia.oleo.data.source.model

class Message() {

     var message: String? = null
     var user: String? = null
     var time: String? = null

    constructor(user: String, text: String, time: String) : this() {
        this.user = user
        this.message = text
        this.time = time
    }
}
