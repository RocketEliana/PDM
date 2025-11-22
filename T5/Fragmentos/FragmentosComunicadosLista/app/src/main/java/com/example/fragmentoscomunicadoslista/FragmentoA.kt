package com.example.fragmentoscomunicadoslista

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragmentoscomunicadoslista.databinding.FragmentFragmentoABinding

class FragmentoA : Fragment() {
    private var _binding: FragmentFragmentoABinding? = null
    private val binding get() = _binding!!

    // 1. Hacer el adaptador global
    private lateinit var adaptador: AdapterHobbit

    // 2. TUS DATOS (Respetando tus imágenes)
    private val listaHobbit = listOf(
        Hobbit("Thorin", R.drawable.thorin),
        Hobbit("Kili", R.drawable.kili),
        Hobbit("Dwalin", R.drawable.dwalin),
        Hobbit("Fili", R.drawable.fili)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFragmentoABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 3. Inicializar el adaptador global
        adaptador = AdapterHobbit(requireContext(), listaHobbit)
        binding.listaHobbits.adapter = adaptador

        // DEBUG: Verificar datos
        println("DEBUG: FragmentoA - Número de elementos: ${listaHobbit.size}")
        println("DEBUG: Adaptador count: ${adaptador.count}")

        // 4. EL LISTENER CORREGIDO
        binding.listaHobbits.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            println("DEBUG: Click en posición: $position")

            val hobbitSeleccionado = listaHobbit[position]
            println("DEBUG: Hobbit seleccionado: ${hobbitSeleccionado.nombre}, Habilitado: ${hobbitSeleccionado.habilitado}")

            // Comprobar si está habilitado (Requisito del menú contextual)
            if (!hobbitSeleccionado.habilitado) {
                Toast.makeText(context, "Elemento deshabilitado", Toast.LENGTH_SHORT).show()
                return@OnItemClickListener
            }

            // ENVIAR DATOS AL FRAGMENT B
            parentFragmentManager.setFragmentResult(
                "clave_hobbit",
                Bundle().apply {
                    putString("nombre", hobbitSeleccionado.nombre)
                    putInt("foto", hobbitSeleccionado.imagen)
                }
            )

            println("DEBUG: Resultado ENVIADO - ${hobbitSeleccionado.nombre}")
        }

        // 5. REGISTRAR EL MENÚ CONTEXTUAL (Requisito del enunciado)
        registerForContextMenu(binding.listaHobbits)
    }

    // --- LÓGICA DEL MENÚ CONTEXTUAL (Habilitar/Deshabilitar) CORREGIDA ---
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.menu_contextual, menu)
        menu.setHeaderTitle("Opciones de Hobbit")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val hobbit = listaHobbit[info.position]

        when (item.itemId) {
            R.id.opcion_habilitar -> {
                hobbit.habilitado = true
                // ✅ NOTIFICAR AL ADAPTADOR QUE LOS DATOS CAMBIARON
                adaptador.notifyDataSetChanged()
                Toast.makeText(context, "Habilitado", Toast.LENGTH_SHORT).show()
            }
            R.id.opcion_deshabilitar -> {
                hobbit.habilitado = false
                // ✅ NOTIFICAR AL ADAPTADOR QUE LOS DATOS CAMBIARON
                adaptador.notifyDataSetChanged()
                Toast.makeText(context, "Deshabilitado", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}