package com.example.bellidolog.presenter

import com.example.bellidolog.model.entity.UsuarioEntity

interface ConfiguracionPresenter {

    fun updatePassword(token: String, user:UsuarioEntity)

    fun successful(msgSuccessful:String)
    fun error(error:String)

}