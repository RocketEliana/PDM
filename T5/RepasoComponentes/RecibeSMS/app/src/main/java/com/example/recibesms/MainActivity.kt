package com.example.recibesms

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.TextView // Importante
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    // Referencia al TextView (si usas binding cámbialo por binding.tuTexto)
    private lateinit var textoPantalla: TextView

    // 1. Creamos el receptor que escuchará DENTRO de la actividad
    private val receptorPantalla = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "BROADCAST_SMS_INTERNO") {
                // ¡AQUÍ OCURRE LA MAGIA! Recibimos los datos
                val mensaje = intent.getStringExtra("sms_cuerpo")
                val emisor = intent.getStringExtra("sms_emisor")

                // Actualizamos el texto en pantalla
                textoPantalla.text = "Último SMS de $emisor:\n\n$mensaje"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Busca tu TextView por ID (Asegúrate que en tu activity_main.xml tienes un TextView con este ID)
        // Si usas binding, borra esta línea y usa binding.nombreDelTextView
        textoPantalla = findViewById(R.id.smsRecibido) // <--- REVISA EL ID EN TU XML
        textoPantalla.text = "Esperando SMS..."

        // ... (Tu código de permisos y canales sigue aquí igual) ...
    }

    // 2. Empezamos a escuchar cuando la app está visible
    override fun onStart() {
        super.onStart()
        val filtro = IntentFilter("BROADCAST_SMS_INTERNO")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(receptorPantalla, filtro, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(receptorPantalla, filtro)
        }
    }

    // 3. Dejamos de escuchar si cerramos la app (para no gastar batería)
    override fun onStop() {
        super.onStop()
        unregisterReceiver(receptorPantalla)
    }

    // ... (El resto de tus funciones de permisos siguen igual) ...
}