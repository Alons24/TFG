package com.example.tfg.Retrofit.Response

import com.example.tfg.Retrofit.DataClases.Mesa

data class MesaResponse(
    var codigo: String,
    var mensaje: String,
    var data: ArrayList<Mesa>
)
