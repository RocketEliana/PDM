package com.example.seekbar

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var media: MediaPlayer
    private lateinit var seek: SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        seek=findViewById(R.id.seek)
        media= MediaPlayer.create(this,R.raw.elpoder);
        media.isLooping=true
        media.start()
        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(
                p0: SeekBar?,
                p1: Int,
                p2: Boolean
            ) {
                // 1. Convertir el entero p1 (0-100) a un flotante (0.0f-1.0f)
                val volume = p1 / 100f

                // 2. Aplicar el volumen a ambos canales (izquierdo y derecho) del reproductor
                media.setVolume(volume, volume)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onDestroy() {
        super.onDestroy() // Llama al método del padre (siempre obligatorio)

        // 1. Detener la reproducción si está activa
        media.stop()

        // 2. Liberar el recurso del sistema (el objeto MediaPlayer)
        media.release()
    }
}