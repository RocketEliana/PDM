package com.example.escudos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class AdapterUnicornio(context: Context, private val lista: List<Unicornio>) :
    ArrayAdapter<Unicornio>(context, 0,lista) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View{
        val itemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false)

        val unicornio = lista[position]

        val imagen = itemView.findViewById<ImageView>(R.id.image)
        val nombre = itemView.findViewById<TextView>(R.id.nombre)

        imagen.setImageResource(unicornio.Imagen)
        nombre.text = unicornio.nombre

        return itemView
    }
}