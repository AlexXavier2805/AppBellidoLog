package com.example.bellidolog.presenter

import com.example.bellidolog.model.entity.HorarioEntity

interface HorarioPresenter {

    fun buscarHorario(token: String, seccionId: Int)
    fun obtenerHorario(lista: List<HorarioEntity>?)
    fun horarioError(error: String)

}