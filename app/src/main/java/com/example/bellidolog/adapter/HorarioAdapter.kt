package com.example.bellidolog.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bellidolog.R
import com.example.bellidolog.model.entity.HorarioEntity

class HorarioAdapter(private val horarios: List<HorarioEntity>) : RecyclerView.Adapter<HorarioAdapter.ViewHolder>(){

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDia: TextView = itemView.findViewById(R.id.tvDiaHorario)
        private val tvCurso: TextView = itemView.findViewById(R.id.tvIdCursoHorario)
        private val tvDocente: TextView = itemView.findViewById(R.id.tvDocenteHorario)
        private val tvHoraInicio: TextView = itemView.findViewById(R.id.tvHoraInicio)
        private val tvHoraFin: TextView = itemView.findViewById(R.id.tvHoraFin)
        private val tvAula: TextView = itemView.findViewById(R.id.tvAula)

        fun bind(horarios: HorarioEntity){
            tvDia.text = horarios.dia.substring(0,3)
            when(horarios.dia){
                "LUNES" -> itemView.setBackgroundColor(Color.GREEN)
                "MARTES" -> itemView.setBackgroundColor(Color.RED)
            }
            tvCurso.text = horarios.asignacion.curso.nombre
            tvDocente.text = horarios.asignacion.trabajador.nombre
            tvHoraInicio.text = horarios.horaInicio
            tvHoraFin.text = horarios.horaFin
            tvAula.text = horarios.seccion.aula.descripcion
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardview_horario,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = horarios[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return horarios.size
    }
}