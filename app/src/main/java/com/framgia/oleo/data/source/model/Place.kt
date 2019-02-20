package com.framgia.oleo.data.source.model

class Place {
    var id: String? = null
    var latitude: String? = null
    var longitude: String? = null
    var address: String? = null
    var time: String? = null

    constructor()

    constructor(id: String?, latitude: String?, longitude: String?, address: String?, time: String?) {
        this.id = id
        this.latitude = latitude
        this.longitude = longitude
        this.address = address
        this.time = time
    }
}
