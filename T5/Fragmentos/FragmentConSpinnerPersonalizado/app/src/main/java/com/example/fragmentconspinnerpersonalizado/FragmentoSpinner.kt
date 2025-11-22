package com.example.fragmentconspinnerpersonalizado

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.fragmentconspinnerpersonalizado.databinding.FragmentSpinnerBinding

class FragmentoSpinner : Fragment() {
    private var _binding: FragmentSpinnerBinding?=null
    private val binding get() =_binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Asignamos el resultado a la variable de respaldo (_binding)
        _binding = FragmentSpinnerBinding.inflate( // 2. Llamamos al método estático 'inflate' de la clase generada por ViewBinding
            inflater,  // 3. Parámetro 1: La herramienta que convierte el XML en Vistas reales
            container, // 4. Parámetro 2: El ViewGroup padre (para calcular tamaños), pero...
            false      // 5. Parámetro 3: ¡IMPORTANTE! 'false' significa "no lo pegues al padre todavía"
        )

// 6. Retornamos la vista raíz (el layout padre del XML) para que el Fragmento la pinte
        return binding.root
    }

    // 1. Sobrescribimos el método. Este se ejecuta INMEDIATAMENTE después de que onCreateView termina.
    override fun onViewCreated(
        view: View,               // 2. La vista YA creada (es lo que devolvió binding.root antes)
        savedInstanceState: Bundle? // 3. El "maletín" de datos (es nulo si es la primera vez, trae datos si giraste la pantalla)
    ) {
        // 4. Llamada al padre para mantener la integridad del ciclo de vida (buena práctica)
        super.onViewCreated(view, savedInstanceState)

        // 5. AQUÍ va tu código: Configurar botones, textos, RecyclerViews, etc.
        val unicornios= listOf(
            Unicornio("Lara",R.drawable.lara),
            Unicornio("Goloso",R.drawable.goloso)
        )
        // 4. Inicialización del Adaptador (El Intermediario)
        // Pasamos 'requireContext()' (o 'this' si tu adaptador lo admite) y la lista de datos.
        val adapter = AdapterUnicornio(requireContext(), unicornios)//constructor
        binding.spinner.adapter = adapter
        // Esto imprimirá: "Lara, Goloso"
        // Configurar el detector de selección
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            // Este método se dispara cuando el usuario elige algo
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // 1. 'position' es el número de la fila elegida (0, 1, 2...)
                // 2. Buscamos ese unicornio en tu lista original usando la posición
                val unicornioElegido = unicornios[position]

                // 3. Actualizamos el texto
                binding.selecciom.text = unicornioElegido.nombre
            }

            // Este método es obligatorio pero casi nunca se usa
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada
            }
        }
        /*this/contextipe,porq?------------------>>>>>>>
1. El problema técnico: "Tú no eres quien yo busco"
Cuando pasas this dentro de un Fragmento, estás pasando un objeto de tipo Fragment. Pero el constructor de tu Adapter (y casi todos los adaptadores en Android) casi seguro pide un objeto de tipo Context.

Una Activity SÍ hereda de Context. Por eso en una Activity puedes usar this.

Un Fragment NO hereda de Context. Es una clase independiente que "vive" dentro de una Activity.

Mira la diferencia en la jerarquía:

MainActivity -> AppCompatActivity -> Activity -> Context (Es un Contexto)

FragmentoSpinner -> Fragment -> Object  (No es un Contexto)*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}
