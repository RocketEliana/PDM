package com.example.explicitoformulario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var nombre: EditText
    private lateinit var edadtxt: TextView
    private lateinit var seek: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        nombre = findViewById(R.id.nombre)
        seek = findViewById(R.id.edad)
        edadtxt = findViewById(R.id.edadtxt)
        val boton = findViewById<Button>(R.id.enviar)
        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(var1: SeekBar?, var2: Int, var3: Boolean) {
                val edad = var2.toString()
                edadtxt.text = edad
            }

            override fun onStartTrackingTouch(var1: SeekBar?) {}
            override fun onStopTrackingTouch(var1: SeekBar?) {}
        })
        boton.setOnClickListener { irDetalle() }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun irDetalle() {
        var nombre = nombre.text.toString()
        var edad = edadtxt.text.toString()
        if (nombre.isEmpty()) {
            Toast.makeText(this, "debe introducir un nombre", Toast.LENGTH_SHORT).show()
        }
        var intent = Intent(this, Detalle::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("edad", edad)
        startActivity(intent)
    }
//__________________>>>IMPORTANTE!!!SE QUEDA EN PAUSA,CUANDO VUELVE ES ONRESUME
    override fun onResume() {
        super.onResume()
        nombre.text.clear()     // limpia el texto
        edadtxt.text = ""
        seek.progress = 18// reinicia el SeekBar
    }
}