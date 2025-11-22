package com.example.fragmentoscomunicadoslista

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class AdapterHobbit(context: Context,private val listaHobbit: List<Hobbit>):
    ArrayAdapter<Hobbit>(context,0,listaHobbit) {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val itemView=convertView?: LayoutInflater.from(context).inflate(R.layout.item_lista,parent,false)
        val hobbit=listaHobbit[position]
        val imagen=itemView.findViewById<ImageView>(R.id.imagenhobbit)
        val nombre=itemView.findViewById<TextView>(R.id.nombrehobbit)
        imagen.setImageResource(hobbit.imagen)
        nombre.setText(hobbit.nombre)
        return  itemView
    }



}