package com.example.bellidolog.model.entity

import java.io.Serializable

data class CargoEntity(
    val cargoId : Int,
    val descripcion : String,
    val estado : Byte
): Serializable
