package com.example.llamada

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest
import android.content.Intent
import android.net.Uri


class MainActivity : AppCompatActivity() {
    private lateinit var numeroT: EditText
    private lateinit var llamar: ImageButton
    val permisoTelefono= registerForActivityResult(ActivityResultContracts.RequestPermission()){
        isGranted->
        if(isGranted){
            Toast.makeText(this,"Permiso concedido", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Permiso de llamada denegado ", Toast.LENGTH_SHORT).show()

        }


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        numeroT=findViewById(R.id.numtelefono)
        llamar=findViewById(R.id.telefono)
        llamar.setOnClickListener { lanzarPermiso(Manifest.permission.CALL_PHONE) }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun lanzarPermiso(permiso:String){
        when{
            ContextCompat.checkSelfPermission(this,permiso)== PackageManager.PERMISSION_GRANTED
                -> hacerllamada()

            else->
                permisoTelefono.launch(permiso)
            }
    }

    fun hacerllamada(){
      val numero=numeroT.text.toString()
            val pattern= Regex("^(?:\\+34|0034)?[ -]?(?:[6-7]\\d{8}|[89]\\d{8})")
        if (!pattern.matches(numero)) {
            Toast.makeText(this, "Número no válido ", Toast.LENGTH_SHORT).show()
            return
        }

        // Lanza la llamada
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$numero"))
        try {
            startActivity(intent)
        } catch (e: SecurityException) {
            Toast.makeText(this, "No se puede realizar la llamada", Toast.LENGTH_SHORT).show()
        }
    }
}

