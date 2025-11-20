package com.example.cesarlinear

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private lateinit var desplaza: Spinner
    private lateinit var codificar: Button
    private lateinit var descodifica: Button //lateinit para objetos,no para primitivos
    private lateinit var spinner: Spinner
    private var mayuscula = true
    private var mayusculaFija = true
    private lateinit var may_min: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        desplaza = findViewById(R.id.spinnersalto)
        display = findViewById(R.id.textContainer)
        may_min = findViewById(R.id.CAPS)
        val delete: Button = findViewById(R.id.DEL)
        val space: Button = findViewById(R.id.SPACE)
        codificar = findViewById(R.id.codificar)
        spinner=findViewById(R.id.spinnersalto)
        descodifica=findViewById(R.id.descodificar)

        descodifica.setOnClickListener { _->descodificaTexto() }

        codificar.setOnClickListener { _ -> codificaTexto()
        descodifica.isEnabled=true
        }
        val lista_letras = listOf(
            R.id.Q,
            R.id.U, R.id.E, R.id.R, R.id.T, R.id.Y, R.id.I, R.id.O, R.id.P, R.id.A, R.id.S,
            R.id.D, R.id.F, R.id.G, R.id.H, R.id.J, R.id.K, R.id.L,
            R.id.Z, R.id.X, R.id.C, R.id.V, R.id.B, R.id.N, R.id.M
        )
        may_min.setOnClickListener {
            configura_min_corto()
        }

        may_min.setOnLongClickListener {
            configura_min_largo()
        }

        configurarBotones(lista_letras)

        delete.setOnClickListener { _ ->
            borra_letras()
        }
        space.setOnClickListener { _ ->
            val espacio = " "
            display.append(espacio)
        }


    }

    private fun configurarBotones(listaId: List<Int>) {
        for (id in listaId) {
            val boton = findViewById<Button>(id)
            boton.setOnClickListener {
                val textoAniadir = if (mayuscula || mayusculaFija)
                    boton.text.toString().uppercase()
                else
                    boton.text.toString().lowercase()

                display.append(textoAniadir)

                // Desactivamos mayúscula temporal si se había activado
                if (mayuscula) mayuscula = false
            }
        }
    }

    private fun borra_letras() {
        val texto = display.text.toString()
        if (texto.isNotEmpty()) {
            display.text = texto.dropLast(1)
            // val textoMostrar = texto.substring(0, texto.length - 1)
            //display.setText(textoMostrar)

        }
    }

    private fun configura_min_corto() {
        if (mayusculaFija) {
            // Si Caps Lock estaba activo, lo desactivamos
            mayusculaFija = false
            may_min.setBackgroundColor(Color.GREEN)
        } else {
            // Activamos mayúscula temporal (Shift)
            mayuscula = true
        }

    }

    private fun configura_min_largo(): Boolean {
        // Activamos Caps Lock
        mayusculaFija = true
        may_min.setBackgroundColor(Color.MAGENTA)
        return true
        /*Valor devuelto	Comportamiento
true	Evento largo consumido → no se llama al clic corto después
false	Evento no consumido → Android puede llamar también al onClick*/

    }

    private fun codificaTexto() {
        // Obtenemos el desplazamiento del Spinner
        val valor = spinner.selectedItem.toString().toInt()  // asumimos que el spinner tiene números

        val texto = display.text.toString()
        val resultado = StringBuilder()

        for (c in texto) {
            // Solo codificamos letras
            val codificado = when (c) {
                in 'A'..'Z' -> 'A' + (c - 'A' + valor) % 26
                in 'a'..'z' -> 'a' + (c - 'a' + valor) % 26
                else -> c  // números, espacios, símbolos se mantienen igual
            }
            resultado.append(codificado)
        }
/*c es un carácter con valor Unicode.

'A' también es un carácter, con valor 65.

Al hacer c - 'A' → estamos normalizando la letra a un rango 0..25:

Ejemplo:

Si c = 'C' → valor 67

'C' - 'A' → 67 - 65 = 2

Luego sumamos el desplazamiento y hacemos % 26 para que quede en 0..25

Finalmente +'A' → volvemos a 65..90*/
        // Mostramos el texto codificado
        display.text = resultado.toString()
    }
    private fun descodificaTexto() {
        val valor = spinner.selectedItem.toString().toInt()  // asumimos que el spinner tiene números

        val texto = display.text.toString()
        val resultado = StringBuilder()

        for (c in texto) {
            // Solo codificamos letras
            val codificado = when (c) {
                in 'A'..'Z' -> 'A' + (c - 'A' - valor) % 26
                in 'a'..'z' -> 'a' + (c - 'a' - valor) % 26
                else -> c  // números, espacios, símbolos se mantienen igual
            }
            resultado.append(codificado)
        }

        // Mostramos el texto codificado
        display.text = resultado.toString()
    }





}
