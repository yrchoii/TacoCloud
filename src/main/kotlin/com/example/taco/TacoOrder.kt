package com.example.taco

import java.io.Serializable
import java.util.*


data class TacoOrder(val serialVersionUID:Long = 1L): Serializable {

    var id: Long? = null
    var deliveryName: String = ""
    var deliveryStreet: String = ""
    var deliveryCity: String = ""
    var deliveryState: String = ""
    var deliveryZip: String = ""
    var ccNumber: String = ""
    var ccExpiration: String = ""
    var ccCVV: String = ""
    var tacos: List<Taco> = ArrayList()
    var placedAt: Date = Date()
    fun addTaco(taco: Taco) {
        this.tacos += taco
    }
}