package com.example.saltachooser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.net.URI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val texto: EditText=findViewById(R.id.edit)
        val boton: Button =findViewById(R.id.boton)
        boton.setOnClickListener { _->
            val url=texto.text.toString()
            if(validaUrl(url)){
                //public Intent(String action, Uri uri) {
                //        throw new RuntimeException("Stub!");
                //    }
                //Intent.ACTION_VIEW, atributo,mira api
                //En ese contexto, "parsear" significa analizar y convertir.
                //
                //Estás convirtiendo un simple texto (un String) que contiene una URL en un objeto Uri
                val intent= Intent(Intent.ACTION_VIEW, Uri.parse(url))
                val chooser=Intent.createChooser(intent,"Abrir con:")
                startActivity(chooser)
                //aunque chooser devuelve un intent,lo que hace es startActivity(chooser): Le das al sistema este segundo mensaje. El sistema lo lee y dice: "¡Entendido! Me pides que muestre mi Activity de diálogo".

            }else{
                Toast.makeText(this,"Direccion invalida", Toast.LENGTH_SHORT).show()
            }
        }







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun validaUrl(url:String) : Boolean{
        val regexUrl=Regex("^(https?:\\/\\/)?([\\w\\-]+\\.)+[a-zA-Z]{2,}(:\\d+)?(\\/[^\\s]*)?\$\n")
        return regexUrl.matches(url)
    }
}