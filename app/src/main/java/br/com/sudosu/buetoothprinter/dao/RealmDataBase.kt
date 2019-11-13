package br.com.sudosu.buetoothprinter.dao

import io.realm.Realm
import io.realm.RealmConfiguration

object RealmDataBase {

    val config = RealmConfiguration.Builder()
        .schemaVersion(1)
        .deleteRealmIfMigrationNeeded() //TODO remove for production
        .build()
    val realm = Realm.setDefaultConfiguration(config)

    val getIntance = Realm.getDefaultInstance()

}