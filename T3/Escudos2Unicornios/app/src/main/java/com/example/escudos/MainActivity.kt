package com.example.escudos

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var lista: ListView
    private lateinit var display: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        lista=findViewById(R.id.list)
        display=findViewById(R.id.text)
        val unicornios = listOf(
            Unicornio(R.drawable.lara, "Lara"),
            Unicornio(R.drawable.bebe, "Bebe"),
            Unicornio(R.drawable.amoroso, "Amoroso"),
            Unicornio(R.drawable.goloso, "Goloso")
        )
        val adapter = AdapterUnicornio(this, unicornios)
        lista.adapter = adapter

// --- AÑADE ESTO ---
        lista.setOnItemClickListener { parent, view, position, id ->
            // 1. Obtienes el objeto Unicornio que fue pulsado
            val unicornioSeleccionado = unicornios[position]
            val nombreSeleccion=unicornioSeleccionado.nombre

            // 2. Asignas el nombre (o cualquier dato) al TextView 'display'
            // (Asumiendo que tu data class Unicornio tiene una propiedad 'nombre')
            display.append("$nombreSeleccion,  ")
        }
    }
}