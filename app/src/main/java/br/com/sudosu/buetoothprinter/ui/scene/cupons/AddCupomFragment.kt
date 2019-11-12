package br.com.sudosu.buetoothprinter.ui.scene.cupons


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.sudosu.buetoothprinter.R
import br.com.sudosu.buetoothprinter.ui.fragments.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class AddCupomFragment : BaseFragment() {

    override var title = ""
    override var colorTitle: Int = R.color.printer_color_white
    override var colorHeader: Int = R.color.printer_color_dark_blue
    override var statusBarTintStyle: StatusBarTintStyle = StatusBarTintStyle.LIGHT
    override var hideHomeButton: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).onSupportNavigateUp()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_cupom, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateTitle(getString(R.string.header_add_cupom))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> onBackPressed()
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onBackPressed(): Boolean {
        requireActivity().onBackPressed()
        return true
    }

}
