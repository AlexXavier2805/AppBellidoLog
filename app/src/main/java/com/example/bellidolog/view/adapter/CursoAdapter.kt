package com.example.bellidolog.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bellidolog.R
import com.example.bellidolog.model.entity.CursoEntity

class CursoAdapter(private val cursos: List<CursoEntity>) : RecyclerView.Adapter<CursoAdapter.ViewHolder>(){

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvIdCurso: TextView = itemView.findViewById(R.id.tvIdCurso)
        private val tvNombre: TextView = itemView.findViewById(R.id.tvNombreCurso)
        private val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcionCurso)
        private val tvGrado: TextView = itemView.findViewById(R.id.tvGrado)

        fun bind(cursos: CursoEntity){
            tvIdCurso.text = cursos.cursoId.toString()
            tvNombre.text = cursos.nombre
            tvDescripcion.text = cursos.descripcion
            tvGrado.text = cursos.grado.nombre
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_cursos,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cursos[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return cursos.size
    }

}