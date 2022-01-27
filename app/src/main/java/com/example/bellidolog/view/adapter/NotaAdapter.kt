package com.example.bellidolog.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bellidolog.R
import com.example.bellidolog.model.entity.NotaEntity

class NotaAdapter(private val notas: List<NotaEntity>) : RecyclerView.Adapter<NotaAdapter.ViewHolder>(){

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNombreCurso: TextView = itemView.findViewById(R.id.tvNombreCursoNota)
        private val tvPrimerBimestre: TextView = itemView.findViewById(R.id.tvPrimerBimestre)
        private val tvSegundoBimestre: TextView = itemView.findViewById(R.id.tvSegundoBimestre)
        private val tvTercerBimestre: TextView = itemView.findViewById(R.id.tvTercerBimestre)
        private val tvCuartoBimestre: TextView = itemView.findViewById(R.id.tvCuartoBimestre)
        fun bind(nota: NotaEntity){
            tvNombreCurso.text = nota.curso.nombre.uppercase()
            tvPrimerBimestre.text = nota.primerBimestre.toString()
            tvSegundoBimestre.text = nota.segundoBimestre.toString()
            tvTercerBimestre.text = nota.tercerBimestre.toString()
            tvCuartoBimestre.text = nota.cuartoBimestre.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_notas,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notas[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return notas.size
    }
}