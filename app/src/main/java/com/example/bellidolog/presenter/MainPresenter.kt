package com.example.bellidolog.presenter

import com.example.bellidolog.model.entity.JwtRequest
import com.example.bellidolog.model.entity.JwtResponse
import com.example.bellidolog.model.entity.MatriculaEntity

interface MainPresenter {

    fun autenticar(jwtRequest: JwtRequest)
    fun buscarMatricula(token: String, username: String)

    fun obtenerToken(jwtResponse: JwtResponse?)
    fun tokenError(error: String)

    fun obtenerMatricula(matriculaEntity: MatriculaEntity?)
    fun matriculaError(error: String)
}