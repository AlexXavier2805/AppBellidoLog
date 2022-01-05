package com.example.bellidolog.model.entity

import java.io.Serializable

data class RolEntity(
    val rolId : Int,
    val descripcion : String,
    val estado : Byte
): Serializable
