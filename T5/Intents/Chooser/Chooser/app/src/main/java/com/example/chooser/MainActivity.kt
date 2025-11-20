package com.example.chooser

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //en esta app voy a hacer un webView que fuerce al so a darme a elegir navegador
    //muy importante meter en el manifest los filter intents
    private lateinit var webView: WebView //para que no se rompa app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        webView=findViewById(R.id.webview)
        webView.settings.javaScriptEnabled=true //buena practica, por deguridad-->Enabled = Habilitado / Activado
        val url=intent?.dataString ?: "https://www.google.es"//-->el elvis me esta diciendo que si no hay otra direccion use esta por defecto
        webView.loadUrl(url)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}