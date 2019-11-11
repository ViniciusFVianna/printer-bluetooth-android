package br.com.sudosu.buetoothprinter

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import br.com.sudosu.buetoothprinter.utils.BluetoothPrinterConstants.NOTIF_CHANNEL_ID
import br.com.sudosu.buetoothprinter.utils.BluetoothPrinterConstants.NOTIF_CHANNEL_NAME

class BluetoothPrintApplication : MultiDexApplication(){

    private val TAG = "LureApplication"

    override fun onCreate() {
        super.onCreate()
        context = this
        appInstance = this

        createNotificationChannels()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null
        private var appInstance: BluetoothPrintApplication? = null

        fun getApplicationContext() = context

        fun getInstance(): BluetoothPrintApplication {
            checkNotNull(appInstance) { "Configure a classe de Application no AndroidManifest.xml" }
            return appInstance!!
        }
    }

    private fun createNotificationChannels() {

        //Cria o canal de notificacao para versoes novas do android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel = NotificationChannel(
                NOTIF_CHANNEL_ID,
                NOTIF_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableLights(true)
                setShowBadge(false)
                lightColor = Color.BLUE
                enableVibration(true)
            }

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "LureApplication.onTerminate()")
    }
}