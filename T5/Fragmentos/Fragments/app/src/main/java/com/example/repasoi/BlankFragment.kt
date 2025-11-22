package com.example.repasoi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class BlankFragment : Fragment() {
    private var nombre: String? = null
    private var apellidos: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nombre = it.getString(ARG_NOMBRE)
            apellidos = it.getString(ARG_APELLIDOS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txtNombre=view.findViewById<TextView>(R.id.nombre)
        val txtApellidos=view.findViewById<TextView>(R.id.apellidos)
        txtNombre.text=nombre
        txtApellidos.text=apellidos

    }

    companion object {
        //Es un objeto único asociado a la clase, no a cada instancia.
        //
        //Sus miembros se comportan como static en Java.
        //
        //Puedes acceder a ellos sin crear un fragment, por ejemplo:
        //
        //BlankFragment.newInstance("Eliana", "Sánchez")
        private const val ARG_NOMBRE = "nombre"
        private const val ARG_APELLIDOS = "apellidos"
        @JvmStatic
        fun newInstance(nombre: String, apellidos: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NOMBRE, nombre)
                    putString(ARG_APELLIDOS, apellidos)
                }
            }
    }
}