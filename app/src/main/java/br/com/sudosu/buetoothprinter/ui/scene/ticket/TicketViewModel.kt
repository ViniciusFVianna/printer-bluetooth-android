package br.com.sudosu.buetoothprinter.ui.scene.ticket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.sudosu.buetoothprinter.busines_logic.ticket.models.ItemTicket
import br.com.sudosu.buetoothprinter.busines_logic.ticket.models.Ticket
import br.com.sudosu.buetoothprinter.busines_logic.ticket.service.TicketRealm
import br.com.sudosu.buetoothprinter.extensions.setValueAndNotify
import io.realm.RealmList
import java.util.*

class TicketViewModel: ViewModel() {

    data class ViewState(
        var id : Long? = null,
        var description: String? = null,
        var timeStamp: Long? = null,
        var value: Double? = null,
        var quantity: Int? = null
    )

    val _viewtate : MutableLiveData<ViewState> = MutableLiveData(ViewState())
    val viewState : LiveData<ViewState>
    get() = _viewtate

    val _itemTicket : MutableLiveData<RealmList<ItemTicket>> = MutableLiveData(RealmList())
    val itemTicket : LiveData<RealmList<ItemTicket>>
    get() = _itemTicket

    val _allTicket: MutableLiveData<List<Ticket>> = MutableLiveData()
    val allTicket: LiveData<List<Ticket>>
    get() = _allTicket

     fun performedTicket(ticket: Ticket){

        ticket.id = TicketRealm.getNextId().toLong()
        ticket.description = _viewtate.value?.description!!
        ticket.timeStamp = _viewtate.value?.timeStamp!!
        ticket.item = _itemTicket.value
        ticket.value = _viewtate.value?.value!!
        ticket.quantity = _viewtate.value?.quantity

        TicketRealm.addTicket(ticket)
    }

     fun getTicket(){
        val realmTicket = TicketRealm.getTicket()
        _allTicket.postValue(realmTicket)

    }

    fun setDescription(description: String?){
         _viewtate.value?.description = description
    }

    fun setTimeStamp(timeStamp: Long?){
        _viewtate.value?.timeStamp = timeStamp
    }
    fun setValue(value: Double?){
        _viewtate.value?.value = value
    }
    fun setQuantity(quantity: Int?){
        _viewtate.value?.quantity = quantity
    }

    fun setItem(itemTicket: RealmList<ItemTicket>){
       _itemTicket.postValue(itemTicket)
    }
}