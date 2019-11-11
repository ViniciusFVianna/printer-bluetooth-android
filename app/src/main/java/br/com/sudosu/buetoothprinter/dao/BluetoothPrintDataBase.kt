package br.com.sudosu.buetoothprinter.dao

import androidx.room.Database
import androidx.room.RoomDatabase


// Define as classes que precisam ser persistidas e a versão do banco
@Database(entities = arrayOf(), version = 1)
abstract class BluetoothPrintDataBase: RoomDatabase() {

}