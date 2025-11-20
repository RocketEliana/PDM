package com.example.lista

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Declaraciones de Vistas y Adaptador
    private lateinit var text: TextView
    private lateinit var boton: Button
    private lateinit var lista: ListView
    private lateinit var adaptador: ArrayAdapter<String>

    // Variable para guardar la elección final (accesible desde cualquier función)
    private var provinciaSeleccionada: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 1. Inicialización de Vistas
        // Asegúrate de que este ID coincida con tu XML
        text = findViewById(R.id.textView2)
        boton = findViewById(R.id.button)
        lista = findViewById(R.id.listView)

        // 2. Configuración del Adaptador
        // El array debe ser declarado como 'val' aquí para que esté disponible en la función manejarSeleccion
        val arrayProvincias = resources.getStringArray(R.array.provincias)
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayProvincias)
        lista.adapter = adaptador

        // 3. Configuración del Listener de la Lista
        // Utilizamos la sintaxis 'object' para evitar el error de Conversión SAM
        lista.setOnItemClickListener(object: AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                // Delega la lógica a una función separada
                manejarSeleccion(position, arrayProvincias)
            }
        })

        // 4. Configuración del Botón
        boton.setOnClickListener { mostrarEleccion() }

        // Código de configuración de bordes de pantalla
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Función que contiene la lógica para manejar el clic en la lista
    private fun manejarSeleccion(position: Int, arrayProvincias: Array<String>) {
        // 1. Obtener los datos
        provinciaSeleccionada = arrayProvincias[position]

        // 2. Actualiza el TextView con la selección
        // Usamos getString(R.string.solucion) que apunta a tu recurso "Solución"
        text.text = getString(R.string.solucion) + ": " + provinciaSeleccionada

        // 3. Muestra un Toast para feedback
        Toast.makeText(this, "Seleccionaste: $provinciaSeleccionada", Toast.LENGTH_SHORT).show()
    }

    // Función que se llama al pulsar el botón
    private fun mostrarEleccion(){
        if (provinciaSeleccionada.isNotEmpty()) {
            Toast.makeText(this, "Tu elección final es: $provinciaSeleccionada", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Por favor, selecciona una provincia primero.", Toast.LENGTH_SHORT).show()
        }
    }
}