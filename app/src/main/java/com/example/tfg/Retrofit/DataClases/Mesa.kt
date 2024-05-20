package com.example.tfg.Retrofit.DataClases
import java.io.Serializable
data class Mesa(
    val idMesa: Int,
    val nombre: String,
    val capacidad: Int,
    val disponible: Boolean,
    val ocupada: Boolean,
    val productos: List<String> // Aquí asumo que los productos se representan con su ID en formato String
): Serializable


