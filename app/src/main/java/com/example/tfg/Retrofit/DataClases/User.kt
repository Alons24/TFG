package com.example.tfg.Retrofit.DataClases

data class User(
    val idUsuario: Int,
    val nombre: String,
    val apellidos: String,
    val email: String,
    val password: String,
    val telefono: String,
    val rol: String
)
