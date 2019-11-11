package br.com.sudosu.buetoothprinter.extensions

fun android.view.View.onClick(l: (v: android.view.View?) -> Unit) {
    setOnClickListener(l)
}