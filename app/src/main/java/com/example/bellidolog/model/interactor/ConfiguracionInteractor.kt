package com.example.bellidolog.model.interactor

import com.example.bellidolog.model.entity.UsuarioEntity

interface ConfiguracionInteractor {

    fun updatePassword(token: String, user: UsuarioEntity)
}