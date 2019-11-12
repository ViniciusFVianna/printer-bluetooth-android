package br.com.sudosu.buetoothprinter.busines_logic.ticket.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Ticket(
    @PrimaryKey
    var id : Long = 0,
    var item: RealmList<ItemTicket>? = null,
    var value: Double = 0.0,
    var quantity: Int = 0
): RealmObject()