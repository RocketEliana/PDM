package com.example.fragmentoscomunicadoslista

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
    private var temaActual = "default" // Control del tema actual

    override fun onCreate(savedInstanceState: Bundle?) {
        // APLICAR TEMA ANTES de setContentView
        aplicarTema()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.barra)

        val esModoHorizontal = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        Log.d(TAG, "Modo horizontal: $esModoHorizontal")

        if (esModoHorizontal) {
            configurarModoHorizontal()
        } else {
            configurarModoVertical()
        }
    }

    private fun configurarModoHorizontal() {
        Log.d(TAG, "Configurando MODO HORIZONTAL")

        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorA, FragmentoA())
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorB, FragmentoB())
            .commit()
    }

    private fun configurarModoVertical() {
        Log.d(TAG, "Configurando MODO VERTICAL")

        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorPrincipal, FragmentoA())
            .commit()

        supportFragmentManager.setFragmentResultListener("clave_hobbit", this) { _, bundle ->
            Log.d(TAG, "Navegando a FragmentoB en modo vertical")

            val fragmentB = FragmentoB().apply {
                arguments = bundle
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.contenedorPrincipal, fragmentB)
                .addToBackStack("fragmento_b")
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.opcion_negro -> {
                cambiarTema("negro")
                true
            }
            R.id.opcion_pais -> {
                cambiarTema("colores")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // MÉTODOS PARA CAMBIAR TEMAS
    private fun aplicarTema() {
        when (temaActual) {
            "negro" -> setTheme(R.style.Theme_FragmentosComunicadosLista_Negro)
            "colores" -> setTheme(R.style.Theme_FragmentosComunicadosLista_ColoresPaises)
            else -> setTheme(R.style.Theme_FragmentosComunicadosLista)
        }
    }

    private fun cambiarTema(nuevoTema: String) {
        temaActual = nuevoTema
        recreate() // Reinicia la actividad para aplicar el nuevo tema
    }
}