package com.example.bellidolog.model.entity

import java.io.Serializable

data class AsignacionEntity(
    val asignacionId : Int,
    val descripcion : String,
    val fecha : String,
    val estado : Byte,
    val trabajador : TrabajadorEntity,
    val curso : CursoEntity
): Serializable
