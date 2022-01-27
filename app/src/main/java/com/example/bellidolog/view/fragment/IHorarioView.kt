package com.example.bellidolog.view.fragment

import com.example.bellidolog.model.entity.HorarioEntity

interface IHorarioView {

    fun obtenerHorario(lista: List<HorarioEntity>?)
    fun horarioError(error: String)

}