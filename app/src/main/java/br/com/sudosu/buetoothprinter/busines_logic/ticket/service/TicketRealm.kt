package br.com.sudosu.buetoothprinter.busines_logic.ticket.service

import br.com.sudosu.buetoothprinter.busines_logic.ticket.models.ItemTicket
import br.com.sudosu.buetoothprinter.busines_logic.ticket.models.Ticket
import br.com.sudosu.buetoothprinter.dao.RealmDataBase

object TicketRealm {

    fun getNextId(): Int {
        RealmDataBase.getIntance.use {
            return try {
                (it.where(ItemTicket::class.java).max("id")?.toInt() ?: -1) + 1
            } catch (ex: ArrayIndexOutOfBoundsException) {
                0
            }
        }
    }

    fun addTicket(ticket: Ticket) {
        RealmDataBase.getIntance.use {
            it.executeTransaction { it.copyToRealmOrUpdate(ticket) }
        }
    }

    fun getTicket(): List<Ticket>{
        RealmDataBase.getIntance.use {
            return it.where(Ticket::class.java).findAll()
        }
    }

}