package br.com.sudosu.buetoothprinter.ui.scene.ticket


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import br.com.sudosu.buetoothprinter.R
import br.com.sudosu.buetoothprinter.ui.adapter.TicketAdapter
import br.com.sudosu.buetoothprinter.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_ticket.*

/**
 * A simple [Fragment] subclass.
 */
class TicketFragment : BaseFragment() {

    private val viewModel : TicketViewModel by activityViewModels()

    override var title = ""
    override var colorTitle: Int = R.color.printer_color_white
    override var colorHeader: Int = R.color.printer_color_dark_blue
    override var statusBarTintStyle: StatusBarTintStyle = StatusBarTintStyle.LIGHT
    override var hideHomeButton: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateTitle(getString(R.string.hader_tickets))

        viewModel._allTicket.observe(viewLifecycleOwner, Observer {
            (recyclerTicket.adapter as TicketAdapter).submitList(it)
        })
    }


}
