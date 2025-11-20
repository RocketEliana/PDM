package com.example.recibesms

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.util.Log // <--- Importante para depurar
import androidx.core.app.NotificationCompat

class SmsReceiver : BroadcastReceiver() {

    private val ID_CANAL = "canal_notificaciones_sms_01"
    private val idNotificacion = 202

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            if (messages.isEmpty()) return

            val sms = messages[0]
            val texto = sms.messageBody
            val emisor = sms.displayOriginatingAddress

            // 1. Notificación (Esto ya te funciona, déjalo igual)
            val manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            // ... (tu código de notificación) ...
            // manager.notify(idNotificacion, notificacion)

            // ---------------------------------------------------------
            // 2. ¡NUEVO! ENVIAMOS EL TEXTO A LA MAINACTIVITY
            // ---------------------------------------------------------
            val intentActualizar = Intent("BROADCAST_SMS_INTERNO")
            intentActualizar.putExtra("sms_cuerpo", texto)
            intentActualizar.putExtra("sms_emisor", emisor)
            intentActualizar.setPackage(context?.packageName) // Solo para mi app
            context?.sendBroadcast(intentActualizar)
        }
    }
}