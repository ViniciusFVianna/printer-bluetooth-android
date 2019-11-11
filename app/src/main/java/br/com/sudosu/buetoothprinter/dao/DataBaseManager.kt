package br.com.sudosu.buetoothprinter.dao

import androidx.room.Room
import br.com.sudosu.buetoothprinter.BluetoothPrintApplication

object DataBaseManager {
    private lateinit var dbInstance: BluetoothPrintDataBase
    init {
        val appContext = BluetoothPrintApplication.getInstance().applicationContext
    dbInstance = Room.databaseBuilder(appContext, BluetoothPrintDataBase::class.java,"bluetoothPrint.sqlite")
        .build()
    }
}