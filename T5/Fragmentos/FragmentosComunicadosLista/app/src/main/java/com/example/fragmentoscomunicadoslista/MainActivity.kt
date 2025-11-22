package com.example.fragmentoscomunicadoslista

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentoscomunicadoslista.databinding.ActivityMainBinding
import android.content.res.Configuration

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.barra)

        val esModoHorizontal = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        Log.d(TAG, "Modo horizontal: $esModoHorizontal")

        if (esModoHorizontal) {
            // ✅ MODO HORIZONTAL: Mostrar ambos fragments
            configurarModoHorizontal()
        } else {
            // ✅ MODO VERTICAL: Mostrar solo FragmentoA inicialmente
            configurarModoVertical()
        }
    }

    private fun configurarModoHorizontal() {
        Log.d(TAG, "Configurando MODO HORIZONTAL")

        // Cargar FragmentoA
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorA, FragmentoA())
            .commit()

        // Cargar FragmentoB vacío
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorB, FragmentoB())
            .commit()
    }

    private fun configurarModoVertical() {
        Log.d(TAG, "Configurando MODO VERTICAL")

        // Cargar FragmentoA inicialmente
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorPrincipal, FragmentoA())
            .commit()

        // Escuchar cuando se seleccione un hobbit para mostrar FragmentoB
        supportFragmentManager.setFragmentResultListener("clave_hobbit", this) { _, bundle ->
            Log.d(TAG, "Navegando a FragmentoB en modo vertical")

            val fragmentB = FragmentoB().apply {
                arguments = bundle
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.contenedorPrincipal, fragmentB)
                .addToBackStack("fragmento_b") // Permite volver atrás
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.opcion_negro -> binding.barra.setBackgroundColor(Color.BLACK)
            R.id.opcion_pais -> binding.barra.setBackgroundColor(Color.RED)
        }
        return super.onOptionsItemSelected(item)
    }
}