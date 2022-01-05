package com.example.bellidolog.presenter

import com.example.bellidolog.model.entity.NotaEntity

interface NotaPresenter {

    fun buscarNotas(token: String, matriculaId: Int)
    fun obtenerNotas(lista: List<NotaEntity>?)
    fun notaError(error: String)

}