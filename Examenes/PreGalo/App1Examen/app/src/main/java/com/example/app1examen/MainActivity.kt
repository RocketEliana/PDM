package com.example.app1examen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app1examen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.enviar.setOnClickListener { _->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("examen://abrir"))
            val nombre=binding.nombre.text.toString()
            val contrasenia=binding.contrasenia.text.toString()
            if(nombre.isEmpty()||contrasenia.isEmpty()){
                Toast.makeText(this,"No puede haber campos vacios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            intent.putExtra("Nombre",nombre)
            intent.putExtra("Contraseña",contrasenia)
            val chooser=Intent.createChooser(intent,"Abrir con:")
            startActivity(chooser)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}