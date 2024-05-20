package com.example.tfg.Retrofit.DataClases

import java.io.Serializable
import java.util.Date

data class Reserva(
    val idReserva: Int,
    val numComensales: Int,
    val fechaReserva: Date,
    val horaReserva: String,
    val nombreCliente: String,
    val telefonoCliente: String,
    val emailCliente: String,
    val observaciones: String?,
    val mesa: String // Aquí asumo que la mesa se representa con su ID en formato String
) : Serializable