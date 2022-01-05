package com.example.bellidolog.presenter

import com.example.bellidolog.model.entity.CursoEntity

interface CursoPresenter {

    fun buscarCursos(token: String, gradoId: Int)

    fun obtenerListaCursos(lista: List<CursoEntity>?)
    fun listaCursosError(error: String)

}