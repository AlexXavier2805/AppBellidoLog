package com.example.bellidolog.view.fragment

import com.example.bellidolog.model.entity.CursoEntity

interface ICursoView {

    fun obtenerListaCursos(lista: List<CursoEntity>?)
    fun listaCursosError(error: String)
}