package com.example.repasoi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajuste de padding para barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Solo añadir fragments la primera vez que se crea la Activity,evitar dos instancias
        if (savedInstanceState == null) {
            // Creamos los fragments usando el patrón newInstance
            val fragment = BlankFragment.newInstance("Eliana", "Sánchez")
            val fragment2 = BlankFragment2.newInstance("Larga", "Ataquines")

            // Transacción de fragments
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, fragment)   // Añade fragment 1
                .add(R.id.fragmentContainerView2, fragment2) // Añade fragment 2
                .commit()
        }
    }
}
