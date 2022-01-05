package com.example.bellidolog.presenter

import com.example.bellidolog.model.entity.AlumnoEntity

interface PerfilPresenter {

    fun updateFoto(token: String, alumnoEntity: AlumnoEntity)

    fun successful(msgSuccessful: String)
    fun error(msgError: String)

}