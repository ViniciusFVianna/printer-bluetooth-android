package br.com.sudosu.buetoothprinter.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.sudosu.buetoothprinter.R
import br.com.sudosu.buetoothprinter.busines_logic.ticket.models.Ticket
import br.com.sudosu.buetoothprinter.utils.BluetoothPrinterConstants.LOCALE_BR
import br.com.sudosu.buetoothprinter.utils.BluetoothPrinterConstants.NORMAL_DATE
import kotlinx.android.synthetic.main.cell_ticket.view.*
import java.text.SimpleDateFormat
import java.util.*

class TicketAdapter (
    val onClick: (Ticket) -> Unit
): ListAdapter<Ticket, TicketAdapter.TicketViewHolder>(Ticket.DiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cell_ticket, parent, false)
        return TicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val view = holder.itemView
        val ticket = getItem(position)
        val dateTime = getTimeStamp(ticket.timeStamp!!)

        with(view){
            ticketDescription.text = ticket.description
            ticketTimestamp.text = dateTime

        }
    }

    private fun getTimeStamp(timeinMillies: Long): String? {
        val date: String?
        val formatter = SimpleDateFormat(NORMAL_DATE, LOCALE_BR)
        date = formatter.format(Date(timeinMillies))
        return date
    }

    class TicketViewHolder(view: View) : RecyclerView.ViewHolder(view)
}