package br.com.sudosu.buetoothprinter.utils

import br.com.sudosu.buetoothprinter.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

object BluetoothPrinterConstants {


    val LOCALE_BR = Locale("pt", "BR")

    //Notifications
    val NOTIF_CHANNEL_ID = "${BuildConfig.APPLICATION_ID}.notificacoes"
    val NOTIF_CHANNEL_NAME = "Notificações Lure"
    val NOTIF_GROUP_ID = "${BuildConfig.APPLICATION_ID}.notificacoes.DEFAULT"

    var ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    const val NORMAL_CLOSURE_STATUS = 1000
    const val NON_BREAKING_SPACE = '\u00A0'
    val REQUIRE_FIELD = "Campo obrigatório"
    const val REQUIRE_PASSWORD: String = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!?@#\\$%\\^&\\*])(?=.{6,15})"
    val REQUIRE_EMAIL = ".+@.+\\..+"

    const val PREFS_TOKEN_AUTH = "PREFS_TOKEN_AUTH"
    const val PREFS_USER_ID = "PREFS_USER_ID"
    const val PREFS_USER_NAME = "PREFS_USER_NAME"
    const val PREFS_USER_THUMB = "PREFS_USER_THUMB"
    const val PREFS_USER_SEND_MESSAGE = "PREFS_USER_SEND_MESSAGE"
    const val PREFS_REQUEST = "PREFS_REQUEST"
    const val PREFS_CHAT_ID = "PREFS_CHAT_ID"
    const val PREFS_CHAT_TO = "PREFS_CHAT_TO"
    const val PREFS_CHAT_MESSAGE = "PREFS_CHAT_MESSAGE"
    const val LIST_OPTIONS = "LIST_OPTIONS"

    const val TIME = "HH:mm"
    const val TIME_AM_PM = "hh:mm a"
    const val NORMAL_DATE = "dd/MM/yyyy"
    const val NOTIFICATION_DATE = "dd/MM/yy"
    const val TIMELINE_DATE = "dd/MM"
    const val SERVER_DATE = "yyyy-MM-dd"

    const val STANDARD_OFFSET = 0

    const val DEFAULT_PAGE_SIZE = 15

    val DATE_FORMAT = SimpleDateFormat(NORMAL_DATE, LOCALE_BR)
    val TIME_FORMAT = SimpleDateFormat(TIME, LOCALE_BR)
    val NOTIFICATION_TIME_FORMAT = SimpleDateFormat("$TIME - $NOTIFICATION_DATE", LOCALE_BR)

    val FORMATTER_DATE_TIMELINE = SimpleDateFormat(TIMELINE_DATE, LOCALE_BR)
    val FORMATTER_DATE_PAYMENT = SimpleDateFormat(NORMAL_DATE, LOCALE_BR)
    val FORMATTER_TIME_TIMELINE = SimpleDateFormat(TIME, LOCALE_BR)
    val FORMATTER_DAY_NAME = SimpleDateFormat("EEEE", LOCALE_BR)
    val FORMATTER_DATE_PREVIOUS_PAYMENT = SimpleDateFormat("MMMM/yyyy", LOCALE_BR)


    // Message types sent from the BluetoothService Handler
    const val MESSAGE_STATE_CHANGE = 1
    const val MESSAGE_READ = 2
    const val MESSAGE_WRITE = 3
    const val MESSAGE_DEVICE_NAME = 4
    const val MESSAGE_TOAST = 5
    const val MESSAGE_CONNECTION_LOST = 6
    const val MESSAGE_UNABLE_CONNECT = 7

    // Intent request codes
    const val REQUEST_CONNECT_DEVICE = 1
    const val REQUEST_ENABLE_BT = 2
    const val REQUEST_CHOSE_BMP = 3
    const val REQUEST_CAMER = 4

    // Key names received from the BluetoothService Handler
    const val DEVICE_NAME = "device_name"
    const val TOAST = "toast"

    //QRcode
    const val QR_WIDTH = 350
    const val QR_HEIGHT = 350

}