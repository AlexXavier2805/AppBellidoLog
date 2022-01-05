package com.example.bellidolog.model.entity

import java.io.Serializable

data class MatriculaEntity(
    val matriculaId : Int,
    val comentario : String,
    val estadoPago : Byte,
    val fechaMatricula : String,
    val estado : Byte,
    val alumno : AlumnoEntity,
    val seccion : SeccionEntity
) : Serializable