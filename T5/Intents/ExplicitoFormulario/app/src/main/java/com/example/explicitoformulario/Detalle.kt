package com.example.explicitoformulario

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Detalle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle)
        val textView: TextView=findViewById(R.id.detalle)
        val volver: Button = findViewById(R.id.volver)
        volver.setOnClickListener { _->onBackPressed() }
        val nom=intent.getStringExtra("nombre")
        val edad=intent.getStringExtra("edad")
        textView.append("Tu nombre es $nom y tu edad $edad")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }



}