package br.com.sudosu.buetoothprinter.extensions

import br.com.sudosu.buetoothprinter.utils.BluetoothPrinterConstants.ISO_FORMAT
import java.text.SimpleDateFormat
import java.util.*

fun Date.toISOFormat(): String {
    val format = SimpleDateFormat(ISO_FORMAT, Locale("pt", "br"))
    return format.format(this)
}