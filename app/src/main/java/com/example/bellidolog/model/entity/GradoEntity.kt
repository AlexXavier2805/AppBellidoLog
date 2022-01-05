package com.example.bellidolog.model.entity

import java.io.Serializable

data class GradoEntity (
    val gradoId: Int,
    val nombre: String,
    val estado: Byte
): Serializable