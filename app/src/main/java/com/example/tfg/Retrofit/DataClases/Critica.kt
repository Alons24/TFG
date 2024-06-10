package com.example.tfg.Retrofit.DataClases

data class Critica(
    var idCritica: String = "",
    var mensaje: String = "",
    var valoracion: Float = 0.0f,
    var nombreUsuario: String = ""
) {
    override fun toString(): String {
        return "Critica(idCritica='$idCritica', mensaje='$mensaje', valoracion=$valoracion, nombreUsuario='$nombreUsuario')"
    }
}