package com.example.bellidolog.model.entity

import java.io.Serializable

data class UsuarioEntity(
    val usuarioId : Int,
    val nombreUsuario : String,
    var contrasenia : String,
    val estado : Byte,
    val rol : RolEntity
): Serializable
