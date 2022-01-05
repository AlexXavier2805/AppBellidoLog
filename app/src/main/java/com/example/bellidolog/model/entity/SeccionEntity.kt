package com.example.bellidolog.model.entity

import java.io.Serializable

data class SeccionEntity(
    val seccionId : Int,
    val nombre : String,
    val estado : Byte,
    val grado : GradoEntity,
    val aula : AulaEntity
): Serializable
