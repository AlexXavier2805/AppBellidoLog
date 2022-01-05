package com.example.bellidolog.view.fragment

import com.example.bellidolog.model.entity.CursoEntity

interface CursoView {

    fun obtenerListaCursos(lista: List<CursoEntity>?)
    fun listaCursosError(error: String)
}