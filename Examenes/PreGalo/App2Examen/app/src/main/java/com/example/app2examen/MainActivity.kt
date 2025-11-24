package com.example.app2examen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app2examen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Lista temporal sin persistencia
        val userList = mutableListOf<User>()

        // Datos que vienen desde otra app
        val nombreR = intent.getStringExtra("Nombre")
        val passwordR = intent.getStringExtra("Contraseña")

        val user = User(nombreR, passwordR)
        val admin = User("admin", "admin")

        // Comprobaciones
        val userExists = userList.any { it == user }
        //any es una función de Kotlin para colecciones (listas, sets, etc.) que devuelve booleano,true->al menos uno
        val adminExists = userList.any { it == admin }

        when {
            // 1. Si viene desde launcher sin datos
            nombreR == null || passwordR == null -> {
                goToRegister("No existe ningún usuario registrado.")
                return
            }

            // 2. Si el usuario NO existe → registrar
            !userExists -> {
                goToRegister("El usuario no existe, debe registrarse.")
                return
            }

            // 3. Si el admin no existe → registrar también
            !adminExists -> {
                goToRegister("No existe administrador en la app.")
                return
            }
        }


    }

    private fun goToRegister(msg: String) {
        val intent = Intent(this, ActivityRegistrer::class.java)
        intent.putExtra("mensaje", msg)
        startActivity(intent)
    }
}
