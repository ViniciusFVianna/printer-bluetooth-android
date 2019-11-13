package br.com.sudosu.buetoothprinter.busines_logic.ticket.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Ticket(
    @PrimaryKey
    var id : Long = 0,
    var description: String? = null,
    var timeStamp: Long? = null,
    var item: RealmList<ItemTicket>? = null,
    var value: Double = 0.0,
    var quantity: Int? = null
): RealmObject(){
    class DiffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<Ticket>(){

        override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
            return oldItem == newItem
        }
    }
}