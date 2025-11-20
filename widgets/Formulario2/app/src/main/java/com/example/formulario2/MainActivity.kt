package com.example.formulario2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var nombre: EditText
    private lateinit var apellidos: EditText
    private lateinit var mail: EditText
    private lateinit var sexo: RadioGroup
    private lateinit var pais: Spinner
    private lateinit var satisfaccion: SeekBar
    private lateinit var boletin: Switch
    private lateinit var display: TextView
    private lateinit var enviar: Button
    private lateinit var txtsatisfaccion: TextView
    private lateinit var listaCheck: List<Int>


    private var valorPais=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        nombre=findViewById(R.id.nombre)
        apellidos=findViewById(R.id.apellidos)
        mail=findViewById(R.id.mail)
        pais=findViewById(R.id.pais)
        listaCheck=listOf(R.id.lectura,R.id.musica,R.id.deporte,R.id.arte)
        sexo=findViewById(R.id.sexo)
        boletin=findViewById(R.id.boletin)
        boletin.setOnClickListener {  }
        txtsatisfaccion=findViewById(R.id.txtsatisfaccion)
        pais.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                valorPais = parent?.getItemAtPosition(position).toString()

            }


            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        satisfaccion=findViewById(R.id.seeksatisfaccion)
        satisfaccion.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            override fun onProgressChanged(
                p0: SeekBar?,
                p1: Int,
                p2: Boolean
            ) {
                txtsatisfaccion.text=p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
        boletin=findViewById(R.id.boletin)
        display=findViewById(R.id.txtsatisfaccion)
        enviar=findViewById(R.id.enviar)
        enviar.setOnClickListener { _->mostrarSeleccion() }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun esEmailValido(email: String): Boolean {
        val regex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        return email.matches(regex)
    }

    private fun mostrarSeleccion() {
        val nombreD = nombre.text.toString().trim()
        val apellidosD = apellidos.text.toString().trim()
        val mailD = mail.text.toString().trim()
        val paisD = pais.selectedItem.toString()  // si pais es un Spinner
        val sexoId = sexo.checkedRadioButtonId

        // Validación de email
        if (!esEmailValido(mailD)) {
            Toast.makeText(this, "Formato de mail inválido", Toast.LENGTH_SHORT).show()
            return
        }

        // Validación de campos vacíos
        if (nombreD.isEmpty() || apellidosD.isEmpty() || mailD.isEmpty() || paisD.isEmpty() || sexoId == -1) {
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Obtener texto del radio seleccionado
        val sexoTexto = findViewById<RadioButton>(sexoId).text.toString()

        // Boletín
        val boletinD = if (boletin.isChecked) {
            "Quiere boletín informativo"
        } else {
            "No desea boletín"
        }

        // Hobbies seleccionados
        val hobbies = listaCheck
            .map { findViewById<CheckBox>(it) }
            .filter { it.isChecked }
            .joinToString(", ") { it.text.toString() }

        // Satisfacción (según tu TextView o EditText)
        val satisfaccionS = txtsatisfaccion.text.toString()


        display.text = """
        Nombre: $nombreD
        Apellidos: $apellidosD
        Mail: $mailD
        País: $paisD
        Sexo: $sexoTexto
        $boletinD
        Hobbies: $hobbies
        Satisfacción: $satisfaccionS
    """.trimIndent()
    }

}