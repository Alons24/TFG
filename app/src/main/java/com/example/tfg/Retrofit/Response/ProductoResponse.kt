package com.example.tfg.Retrofit.Response

import com.example.tfg.Retrofit.DataClases.Producto

data class ProductoResponse(
    var codigo: String,
    var mensaje: String,
    var data: ArrayList<Producto>
)
