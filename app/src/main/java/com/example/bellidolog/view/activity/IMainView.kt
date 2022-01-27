package com.example.bellidolog.view.activity

import com.example.bellidolog.model.entity.JwtResponse
import com.example.bellidolog.model.entity.MatriculaEntity

interface IMainView {

    fun obtenerToken(jwtResponse: JwtResponse?)
    fun tokenError(error: String)

    fun obtenerMatricula(matriculaEntity: MatriculaEntity?)
    fun matriculaError(error: String)
}