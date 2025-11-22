package com.example.fragmentconspinnerpersonalizado

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class AdapterUnicornio(context: Context,private val lista: List<Unicornio>): ArrayAdapter<Unicornio>(context,0,lista){
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val itemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false)

        val unicornio = lista[position]

        val imagen = itemView.findViewById<ImageView>(R.id.unic)
        val nombre = itemView.findViewById<TextView>(R.id.nombreUnic)

        imagen.setImageResource(unicornio.imagen)
        nombre.text = unicornio.nombre

        return itemView

    }
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }

}