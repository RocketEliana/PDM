package com.example.calcukotlin

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var pantalla: TextView
    private lateinit var listaNum: List<Int>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pantalla = findViewById(R.id.pantalla)
        val reultado=findViewById<Button>(R.id.igual)
        reultado.setOnClickListener { _->
            resultado() }
        val botonBorrar = findViewById<Button>(R.id.borrar)
        botonBorrar.setOnClickListener {
            borrarUltimoValor() // Llama a la función de borrado
        }

        listaNum = listOf(
            R.id.uno, R.id.dos, R.id.tres, R.id.cuatro,
            R.id.cinco, R.id.seis, R.id.siete, R.id.ocho,
            R.id.nueve, R.id.cero, R.id.c, R.id.mas,
            R.id.diovision, R.id.multiplicacion, R.id.resta
        )

        for (id in listaNum) {
           val bot = findViewById<Button>(id)//local para que se actualice a cada vuelta
            bot.setOnClickListener {
                val textoBoton = bot.text.toString()
                asignarValor(textoBoton)
            }
        }


        }

    private fun borrarUltimoValor() {
        val textoActual = pantalla.text.toString()
        if (textoActual.isNotEmpty()) {
            pantalla.text = textoActual.substring(0, textoActual.length - 1)
        }
    }
    private fun asignarValor(texto: String) {
        // 1. AÑADIMOS ESTA COMPROBACIÓN
        if (texto == "C") {
            pantalla.text = "" // Si es "C", borramos todo
            return // Y salimos de la función
        }

        // 2. Si no es "C", el resto del código funciona como antes
        if (pantalla.text.isEmpty()) {
            pantalla.text = texto
        } else {
            pantalla.append(texto)
        }
    }
    private fun resultado() {
        val operacion = pantalla.text.toString()

        // 1. Variables para guardar el operador y su posición
        var operador: Char? = null
        var posicion = -1

        // 2. Buscamos el operador y su posición en el string
        // Empezamos en el índice 1 para ignorar un posible signo negativo al principio (ej: -5+10)
        for (i in 1 until operacion.length) {
            if (operacion[i] in "+-*/") {
                operador = operacion[i]
                posicion = i
                break // Encontramos el operador, salimos del bucle
            }
        }

        // 3. Si no encontramos un operador, la expresión es inválida
        if (operador == null || posicion == -1) {
            pantalla.text = "Error"
            return
        }

        try {
            // 4. Extraemos el primer y segundo número usando la posición del operador
            val num1Str = operacion.substring(0, posicion)
            val num2Str = operacion.substring(posicion + 1)

            // Si alguna de las partes está vacía (ej: "5*"), es un error
            if (num1Str.isEmpty() || num2Str.isEmpty()) {
                pantalla.text = "Error"
                return
            }

            val num1 = num1Str.toDouble()
            val num2 = num2Str.toDouble()

            var resultadoFinal: Double? = null

            // 5. Calculamos el resultado (esta parte es idéntica a la anterior)
            when (operador) {
                '+' -> resultadoFinal = num1 + num2
                '-' -> resultadoFinal = num1 - num2
                '*' -> resultadoFinal = num1 * num2
                '/' -> {
                    if (num2 != 0.0) {
                        resultadoFinal = num1 / num2
                    } else {
                        pantalla.text = "Error"
                        return
                    }
                }
            }

            // 6. Mostramos el resultado en la pantalla
            if (resultadoFinal!! % 1 == 0.0) {
                pantalla.text = resultadoFinal.toInt().toString()
            } else {
                pantalla.text = resultadoFinal.toString()
            }

        } catch (e: Exception) {
            pantalla.text = "Error"
        }
    }
}
