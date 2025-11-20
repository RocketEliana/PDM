package com.example.radiobutton

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var texto: TextView
    private lateinit var radiogroup: RadioGroup
    private lateinit var boton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        radiogroup = findViewById(R.id.radioGroup)
        texto = findViewById(R.id.textseleccionado)
        boton = findViewById(R.id.botoneleccion)
        boton.setOnClickListener {
            mostrartexto()
        //podria hacer una lambda con un parametro aninimo pero lo ideal es
        // que el onCreate este lo mas limpio posible por un tema de mantenimiento y escalabilidad
        }
    }
    fun mostrartexto(){
        val seleccion = radiogroup.checkedRadioButtonId
        if (seleccion != -1) {

            val radiobutton = findViewById<RadioButton>(seleccion)
            val textoseleccionado = radiobutton.text.toString()
            texto.text = getString(R.string.has_seleccionado, textoseleccionado)
            //Deseleccionar todos los RadioButton en el grupo
            radiogroup.clearCheck()
        } else {
            Toast.makeText(this, getString(R.string.selecciona_una_opcion), Toast.LENGTH_SHORT).show()

        }

    }
}
