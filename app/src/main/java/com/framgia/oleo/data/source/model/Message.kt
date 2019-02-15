package com.framgia.oleo.data.source.model

class Message() {

    var id: String? = null
    var message: String? = null
    var userId: String? = null
    var time: String? = null

    constructor(id: String, userId: String, text: String, time: String) : this() {
        this.id = id
        this.userId = userId
        this.message = text
        this.time = time
    }
}
