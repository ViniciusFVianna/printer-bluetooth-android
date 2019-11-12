package br.com.sudosu.buetoothprinter.ui.scene.ticket


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.sudosu.buetoothprinter.R
import br.com.sudosu.buetoothprinter.ui.fragments.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class TicketFragment : BaseFragment() {
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


}
