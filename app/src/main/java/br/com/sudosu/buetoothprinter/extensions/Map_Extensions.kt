package br.com.sudosu.buetoothprinter.extensions

import android.os.Bundle

fun Map<String,String>.toBundle(): Bundle {
    val bundle = Bundle()
    for (key in keys) {
        bundle.putString(key, get(key))
    }
    return bundle
}