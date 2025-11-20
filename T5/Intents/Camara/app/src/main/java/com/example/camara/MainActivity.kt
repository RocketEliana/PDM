package com.example.camara

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest


class MainActivity : AppCompatActivity() {
    private lateinit var imagen:ImageView
    //variables de clase que contienen contratos,lo declaro asi por que
    //me ahorro manejar nulos
    //private val miLauncherCam = registerForActivityResult(
    //    ActivityResultContracts.TakePicturePreview(),
    //    ActivityResultCallback<Bitmap> { bitmap ->
    //        // usar bitmap
    //    }
    //)como el  segundo es una lambda-->idiomatico de kotlin
    private val  milauchercam=registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
        bitmap->
        if(bitmap !=null){
            imagen.setImageBitmap(bitmap)
        }
    }
    private val miLauncherDePermiso = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            milauchercam.launch(null)//→ Literalmente significa “lanza esto sin parámetros”.
        } else {
            Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        imagen=findViewById(R.id.imageView)
        val boton=findViewById<Button>(R.id.boton)
        boton.setOnClickListener { comprobarPermisoYlanzarCamara() }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    private fun comprobarPermisoYlanzarCamara() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                milauchercam.launch(null)
            }
            else -> {
                miLauncherDePermiso.launch(Manifest.permission.CAMERA)

            }
        }
    }
}