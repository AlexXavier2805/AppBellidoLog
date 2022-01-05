package com.example.bellidolog.model.interactor

import com.example.bellidolog.model.entity.AlumnoEntity

interface PerfilInteractor {

    fun updateFoto(token: String, alumnoEntity: AlumnoEntity)

}