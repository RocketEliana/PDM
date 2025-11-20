package com.example.enviosms

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts // Importante
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.enviosms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // 1. DEFINIMOS EL LANZADOR DE PERMISOS (Estilo Moderno)
    // Esto sustituye a onRequestPermissionsResult
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Si el usuario dice SÍ, intentamos enviar el SMS
            // Recuperamos los datos de los campos de texto nuevamente
            val numero = comprobarNumero()
            val smsEnvio = binding.sms.text.toString().trim()

            // Validamos que no estén vacíos antes de enviar
            if (numero != null && smsEnvio.isNotEmpty()) {
                enviarSMS(numero, smsEnvio)
            }
        } else {
            // Si el usuario dice NO
            Toast.makeText(this, "Permiso SMS denegado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enviar.setOnClickListener {
            // Primero validamos los datos (número y mensaje)
            val numero = comprobarNumero() ?: return@setOnClickListener
            val smsEnvio = binding.sms.text.toString().trim()

            if (smsEnvio.isEmpty()) {
                Toast.makeText(this, "El mensaje está vacío", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. COMPROBAMOS EL PERMISO
            when {
                // CASO A: Ya tenemos el permiso concedido -> Enviamos directo
                ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED -> {
                    enviarSMS(numero, smsEnvio)
                }
                // CASO B: No tenemos permiso -> Lanzamos la pregunta al usuario
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.SEND_SMS)
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Este método no cambia, sigue igual
    private fun enviarSMS(numero: String, mensaje: String) {
        try {
            val smsManager: SmsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                getSystemService(SmsManager::class.java)
            } else {
                SmsManager.getDefault()
            }

            smsManager.sendTextMessage(numero, null, mensaje, null, null)
            Toast.makeText(this, "SMS enviado a $numero", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al enviar SMS: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    // Este método tampoco cambia
    private fun comprobarNumero(): String? {
        val numero = binding.telefono.text.toString().trim()
        // Ojo: He escapado el signo $ al final del regex para Kotlin
        val pattern = Regex("^(?:\\+34|0034)?[ -]?(?:[6-7]\\d{8}|[89]\\d{8})\$")

        return if (pattern.matches(numero)) {
            numero
        } else {
            Toast.makeText(this, "Número no válido", Toast.LENGTH_SHORT).show()
            null
        }
    }
}