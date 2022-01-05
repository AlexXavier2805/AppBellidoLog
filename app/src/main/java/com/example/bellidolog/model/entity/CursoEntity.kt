package com.example.bellidolog.model.entity

import java.io.Serializable

data class CursoEntity(
    val cursoId: Int,
    val nombre: String,
    val descripcion: String,
    val estado: Byte,
    val grado: GradoEntity,
): Serializable
