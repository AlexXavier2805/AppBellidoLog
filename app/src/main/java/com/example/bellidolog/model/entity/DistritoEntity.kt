package com.example.bellidolog.model.entity

import java.io.Serializable

data class DistritoEntity(
    val distritoId : Int,
    val nombre : String,
    val estado : Byte
): Serializable
