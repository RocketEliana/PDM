package com.example.repasoi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


private const val ARG_DIRECCION = "direccion"
private const val ARG_LOCALIDAD= "localidad"

class BlankFragment2 : Fragment() {
    private var direccion: String? = null
    private var localidad: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            direccion= it.getString(ARG_DIRECCION)
            localidad= it.getString(ARG_LOCALIDAD)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val direccionTxt=view.findViewById<TextView>(R.id.direccion)
        val localidadTxt=view.findViewById<TextView>(R.id.localidad)
        localidadTxt.text=localidad
        direccionTxt.text=direccion

    }

    companion object {
        @JvmStatic
        fun newInstance(direccion: String, localidad: String) =
            BlankFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_DIRECCION, direccion)
                    putString(ARG_LOCALIDAD, localidad)
                }
            }
    }
}