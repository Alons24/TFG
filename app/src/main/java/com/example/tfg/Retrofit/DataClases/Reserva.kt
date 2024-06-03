package com.example.tfg.Retrofit.DataClases

import java.io.Serializable
import java.util.Date

data class Reserva(
    val idReserva: String = "",
    val numComensales: Int = 0,
    val fechaReserva: String = "",
    val horaReserva: String = "",
    val nombreCliente: String = "",
    val telefonoCliente: String = "",
    val emailCliente: String = "",
    val observaciones: String = "",
) {}