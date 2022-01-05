package com.example.bellidolog.model.entity

import java.io.Serializable

data class HorarioEntity(
    val horarioId : Int,
    val horaInicio : String,
    val horaFin : String,
    val dia : String,
    val estado : Byte,
    val asignacion : AsignacionEntity,
    val seccion : SeccionEntity
): Serializable
