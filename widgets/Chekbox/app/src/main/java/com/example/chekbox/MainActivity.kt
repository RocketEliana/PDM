package com.example.chekbox

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Arrays

class MainActivity : AppCompatActivity() {
    private lateinit var boton: Button
    private lateinit var cheks: Array<CheckBox>
    private lateinit var text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        boton=findViewById(R.id.button2)
        text=findViewById(R.id.textView)

        val check1=findViewById<CheckBox>(R.id.chvalladolid)
        val check2=findViewById<CheckBox>(R.id.segovia)
        val check3=findViewById<CheckBox>(R.id.palencia)
        cheks=arrayOf(check1,check2,check3)
        boton.setOnClickListener { pasoActyvity() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun pasoActyvity(){

        // 1. Filtrar los CheckBoxes marcados y mapear sus textos a una lista de Strings
        val listaSeleccionados = cheks
            .filter { it.isChecked }            // Filtra solo los CheckBoxes que están marcados (isChecked = true)
            .map { it.text.toString() }         // Convierte cada CheckBox filtrado en su texto (String)
            .toMutableList()                    // Convierte el resultado final en una lista mutable (aunque aquí no es estrictamente necesario)

        // 2. Formatear la lista para mostrarla
        val textoResultado: String

        if (listaSeleccionados.isEmpty()) {
            // Si la lista está vacía
            textoResultado = "No se ha seleccionado ninguna ciudad."
        } else {
            // Unir los elementos de la lista en un solo String.
            val ciudades = listaSeleccionados.joinToString(separator = ", ")
            textoResultado = "Ciudades seleccionadas: $ciudades"
        }

        // 3. Asignar el texto final al TextView
        text.text = textoResultado
    }
}



