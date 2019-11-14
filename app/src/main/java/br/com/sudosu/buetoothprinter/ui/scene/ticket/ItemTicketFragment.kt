package br.com.sudosu.buetoothprinter.ui.scene.ticket


import android.os.Bundle
import android.text.format.DateUtils
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import br.com.sudosu.buetoothprinter.R
import br.com.sudosu.buetoothprinter.busines_logic.ticket.models.ItemTicket
import br.com.sudosu.buetoothprinter.busines_logic.ticket.models.Ticket
import br.com.sudosu.buetoothprinter.extensions.string
import br.com.sudosu.buetoothprinter.ui.fragments.BaseFragment
import br.com.sudosu.buetoothprinter.utils.BluetoothPrinterConstants
import kotlinx.android.synthetic.main.fragment_item_ticket.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ItemTicketFragment : BaseFragment() {

    private val viewModel: TicketViewModel by activityViewModels()

    override var title: String = ""
    override var colorTitle: Int = R.color.printer_color_white
    override var colorHeader: Int = R.color.printer_color_dark_blue
    override var hideHomeButton: Boolean = false
    override var statusBarTintStyle: StatusBarTintStyle = StatusBarTintStyle.LIGHT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (requireActivity() as AppCompatActivity).onSupportNavigateUp()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_ticket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateTitle(getString(R.string.header_itens))
        val calendar = Calendar.getInstance().timeInMillis
//        itemDate.text = getTimeStamp(calendar)

        viewModel.setDescription(itemDescriptionText.string)
        viewModel.setTimeStamp(calendar)
        viewModel.viewState.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            itemDate.text = getTimeStamp(it.timeStamp!!)
        })
    }

    private fun saveItem(ticket: Ticket){
        viewModel.performedTicket(ticket)
    }

    private fun getTimeStamp(timeinMillies: Long): String? {
        val date: String?
        val formatter = SimpleDateFormat(BluetoothPrinterConstants.NORMAL_DATE, BluetoothPrinterConstants.LOCALE_BR)
        date = formatter.format(Date(timeinMillies))
        return date
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home -> onBackPressed()
            else ->super.onOptionsItemSelected(item)
        }
    }

    fun onBackPressed(): Boolean {
        requireActivity().onBackPressed()
        return true
    }


}
