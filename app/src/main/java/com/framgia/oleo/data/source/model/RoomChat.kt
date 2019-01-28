package com.framgia.oleo.data.source.model

class RoomChat() {
    var roomId: String? = null
    var userFriendId: String? = null
    var messageList: MutableList<Message>? = null

    constructor(roomId: String, userFriendId: String, messageList: MutableList<Message>) : this() {
        this.roomId = roomId
        this.userFriendId = userFriendId
        this.messageList = messageList
    }
}
