package com.example.eligenavegador

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.style.TabStopSpan
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eligenavegador.databinding.ActivityMainBinding
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.boton.setOnClickListener {
            var direccionUrl = binding.url.text.toString().trim()

            if (direccionUrl.isNotEmpty() && !direccionUrl.startsWith("http")) {
                direccionUrl = "https://$direccionUrl"
            }
            val seguir = validaUrl(direccionUrl)
            if (!seguir) {
                Toast.makeText(
                    this, "Direccion invalida",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener//volver al click

            }
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = direccionUrl.toUri()
            val intentChooser = Intent.createChooser(intent, "Que navegador deseas")
            startActivity(intentChooser)
            binding.url.text.clear()

        }
    }

    private fun validaUrl(url: String): Boolean {
        return android.util.Patterns.WEB_URL.matcher(url).matches()
    }
}