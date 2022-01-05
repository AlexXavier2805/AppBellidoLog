package com.example.bellidolog.model.entity

import java.io.Serializable

data class AulaEntity(
    val aulaId : Int,
    val descripcion : String,
    val estado : Byte
): Serializable
