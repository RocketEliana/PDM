package com.example.escudosspinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val spinner=findViewById<Spinner>(R.id.spinner)
        val textView=findViewById<TextView>(R.id.textView)
        val listaUnic= listOf(Unicornio(R.drawable.goloso,"Goloso"),
            Unicornio(R.drawable.amoroso,"Amoroso"),
            Unicornio(R.drawable.bebe,"Bebe"),
            Unicornio(R.drawable.lara,"Lara"))
        val adapter= AdapterUnicornio(this,listaUnic)
        spinner.adapter=adapter
        //en lista setOnItem,el spinner es diferente
        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                val unicornioSeleccionado = listaUnic[p2]
                val nombreUnSeleccionado=unicornioSeleccionado.nombre
                textView.setText("$nombreUnSeleccionado")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }
}