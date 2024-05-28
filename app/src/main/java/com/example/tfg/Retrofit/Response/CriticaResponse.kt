package com.example.tfg.Retrofit.Response

import com.example.tfg.Retrofit.DataClases.Critica


data class CriticaResponse(
    var codigo: String,
    var mensaje: String,
    var data: ArrayList<Critica>
)
