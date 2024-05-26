package com.example.tfg.Retrofit.Response

import com.example.tfg.Retrofit.DataClases.User

data class UserResponse(
    var codigo: String,
    var mensaje: String,
    var data: ArrayList<User>,
    val toke: String
)
