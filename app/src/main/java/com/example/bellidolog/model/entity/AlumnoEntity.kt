package com.example.bellidolog.model.entity

import java.io.Serializable

data class AlumnoEntity(
    val alumnoId : Int,
    val dni : String,
    val nombre : String,
    val apellidoPaterno : String,
    val apellidoMaterno : String,
    val direccion : String,
    val telefono : String,
    val fechaNacimiento : String,
    var foto: String,
    val estado : Byte,
    val distrito : DistritoEntity?,
    val apoderado : ApoderadoEntity?,
    val usuario : UsuarioEntity
) : Serializable
