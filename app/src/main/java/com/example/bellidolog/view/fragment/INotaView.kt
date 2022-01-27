package com.example.bellidolog.view.fragment

import com.example.bellidolog.model.entity.NotaEntity

interface INotaView {

    fun obtenerNotas(lista: List<NotaEntity>?)
    fun notaError(notasError: String)

}