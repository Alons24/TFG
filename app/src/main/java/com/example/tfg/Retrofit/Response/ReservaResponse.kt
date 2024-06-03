package com.example.tfg.Retrofit.Response

import com.example.tfg.Retrofit.DataClases.Reserva

data class ReservaResponse(
    var codigo: String,
    var mensaje: String,
    var data: List<Reserva>
)
