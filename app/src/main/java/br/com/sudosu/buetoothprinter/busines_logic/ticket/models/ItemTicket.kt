package br.com.sudosu.buetoothprinter.busines_logic.ticket.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ItemTicket(
    @PrimaryKey
    var id : Long = 0,
    var description: String? = null,
    var type: String? = null,
    var value: Double = 0.0
): RealmObject()