package com.example.bellidolog.model.interactor

import com.example.bellidolog.model.entity.JwtRequest

interface MainInteractor {

    fun autenticar(jwtRequest: JwtRequest)
    fun buscarMatricula(token: String, username: String)
}