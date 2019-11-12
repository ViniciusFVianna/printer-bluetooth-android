package br.com.sudosu.buetoothprinter.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import br.com.sudosu.buetoothprinter.R
import kotlinx.android.synthetic.main.toolbar_main.*
import java.util.*
import kotlin.collections.HashMap

abstract class BaseFragment : Fragment(){

    private val ARG_REQUEST_CODE = "r_code"
    private val ARG_REQUESTING_FRAGMENT = "r_frag"

    class SharedViewModel: ViewModel() {
        /**
         * Guarda os retornos de todos os fragmentos
         *
         * {
         *      UUIDFragAtual : {
         *          123 : <ResultCode, Bundle>,
         *          456 : <ResultCode, Bundle>
         *      }
         *      UIDFragAtual2 : {
         *          123 : <ResultCode, Bundle>,
         *          456 : <ResultCode, Bundle>
         *      }
         * }
         */
        val resultsLiveData = MutableLiveData<HashMap<String, HashMap<Int, Pair<Int, Bundle>>>>(
            HashMap()
        )
    }
    enum class StatusBarTintStyle { LIGHT, DARK }
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _receivedRequestCode: Int? = null
    private var _requestingFragment: String? = null

    private val _fragmentUUID: String = UUID.randomUUID().toString()

    open var statusBarTintStyle: StatusBarTintStyle = StatusBarTintStyle.LIGHT

    open var hideToolbar = false

    abstract var title: String

    abstract var colorTitle: Int

    abstract var colorHeader: Int

    open var hideHomeButton: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarMain?.isVisible = !hideToolbar

        updateTitle(title)
        updateTitleColor(colorTitle)
        updateStatusBarTint(statusBarTintStyle)
        updateHeaderColor(colorHeader)

        toolbarMain?.also {
            (requireActivity() as AppCompatActivity).also {
                it.setSupportActionBar(toolbarMain)
                it.supportActionBar?.setDisplayShowTitleEnabled(false)
                it.supportActionBar?.setDisplayHomeAsUpEnabled(!hideHomeButton)
                it.supportActionBar?.setHomeButtonEnabled(true)
                it.supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_back)

            }
        }

    }

    fun updateTitle(title: String) {
        this.title = title
        toolbarTitle?.text = title
    }

    private fun updateTitleColor(color: Int){
        toolbarTitle.setTextColor(ContextCompat.getColor(requireContext(), color))
    }

    private fun updateHeaderColor( color: Int){
        toolbarMain.setBackgroundColor(ContextCompat.getColor(requireContext(), color))
    }

    private fun updateStatusBarTint(statusBarTintStyle: StatusBarTintStyle) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            var flags = activity?.window?.decorView?.systemUiVisibility ?: return

            flags = if (statusBarTintStyle == StatusBarTintStyle.DARK) {
                flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

            } else {
                flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() //Ver aqui como inverter a flag
            }

            activity?.window?.decorView?.systemUiVisibility = flags
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _receivedRequestCode = arguments?.getInt(ARG_REQUEST_CODE)
        _requestingFragment = arguments?.getString(ARG_REQUESTING_FRAGMENT)
    }

    override fun onResume() {
        super.onResume()

        //Verifica se nao existe resultado no vm para qualquer request feita.
        sharedViewModel.resultsLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it[_fragmentUUID]?.entries?.forEach { requestCodeResult ->
                onFragmentResult(requestCode = requestCodeResult.key, resultCode = requestCodeResult.value.first, data = requestCodeResult.value.second)
            }
            //Limpa depois de processar todos
            it.remove(_fragmentUUID)
        })
    }

    /**
     * Funcoes chamadas para criar um fragmento que se espera resultados.
     */

    fun navigateForResults(destinationId: Int, requestCode: Int) {
        navigateForResults(destinationId, requestCode, Bundle())
    }

    fun navigateForResults(destinationId:Int, requestCode: Int, arguments:Bundle) {
        arguments.putInt(ARG_REQUEST_CODE, requestCode)
        arguments.putString(ARG_REQUESTING_FRAGMENT, _fragmentUUID)
        findNavController().navigate(destinationId, arguments, null)
    }

    /**
     * Chamada pelo fragmento antes de se encerrar, colocando um resultado.
     */
    fun setResult(resultCode: Int, data: Bundle) {
        val allResults = sharedViewModel.resultsLiveData.value!!
        val reqRes = allResults.getOrPut(_requestingFragment ?: return) { HashMap() }
        reqRes[_receivedRequestCode!!] = Pair(resultCode, data)
        allResults[_requestingFragment!!] = reqRes
        sharedViewModel.resultsLiveData.value = allResults
    }

    open fun onFragmentResult(requestCode:Int, resultCode: Int, data:Bundle) { }

    override fun onDestroy() {
        super.onDestroy()
        sharedViewModel.resultsLiveData.value?.remove(_fragmentUUID)
    }
}