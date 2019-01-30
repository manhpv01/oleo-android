package com.framgia.oleo.data.source.model

class RoomChat() {
    var roomId: String? = null
    var userFriendId: String? = null
    var messageList: MutableList<Message>? = null
    var userFriendName: String? = null

    constructor(roomId: String, userFriendId: String, messageList: MutableList<Message>, userFriendName: String) : this() {
        this.roomId = roomId
        this.userFriendId = userFriendId
        this.messageList = messageList
        this.userFriendName = userFriendName
    }
}
