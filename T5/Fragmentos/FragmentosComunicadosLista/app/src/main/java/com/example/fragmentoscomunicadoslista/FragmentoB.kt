package com.example.fragmentoscomunicadoslista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentoscomunicadoslista.databinding.FragmentFragmentoBBinding

class FragmentoB : Fragment() {
    private var _binding: FragmentFragmentoBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFragmentoBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("DEBUG: FragmentoB - Inicializado")
        println("DEBUG: FragmentoB - Arguments: $arguments")

        // 1. PROCESAR ARGUMENTS INICIALES (para cuando venimos del modo vertical)
        arguments?.let { bundle ->
            val nombre = bundle.getString("nombre")
            val foto = bundle.getInt("foto", -1)

            println("DEBUG: FragmentoB - Arguments recibidos: $nombre, $foto")

            binding.textofragmentob.text = nombre ?: "Selecciona un hobbit"
            if (foto != -1) {
                binding.imageView.setImageResource(foto)
            }
        }

        // 2. ESCUCHAR EN VIVO (para modo horizontal y actualizaciones)
        parentFragmentManager.setFragmentResultListener("clave_hobbit", viewLifecycleOwner) { requestKey, bundle ->
            val nombre = bundle.getString("nombre")
            val foto = bundle.getInt("foto", -1)

            println("DEBUG: FragmentoB - Datos recibidos en vivo: $nombre, $foto")

            binding.textofragmentob.text = nombre ?: "Selecciona un hobbit"
            if (foto != -1) {
                binding.imageView.setImageResource(foto)
            }
        }

        // 3. ESTADO INICIAL (si no hay datos)
        if (arguments == null) {
            binding.textofragmentob.text = "Selecciona un hobbit"
            // Opcional: establecer una imagen por defecto
            // binding.imageView.setImageResource(R.drawable.placeholder)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}