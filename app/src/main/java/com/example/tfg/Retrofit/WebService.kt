package com.example.tfg.Retrofit

import com.example.tfg.Retrofit.DataClases.Critica
import com.example.tfg.Retrofit.DataClases.Reserva
import com.example.tfg.Retrofit.Response.CriticaResponse
import com.example.tfg.Retrofit.Response.ReservaResponse
import com.example.tfg.Retrofit.Response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WebService {
    data class Request(
        val email: String,
        val password: String,
        val nombre: String,
        val apellidos: String,
        val telefono: String,
        val rol: String,
        val idUsuario: String
    )

    @GET("/reservas")
    suspend fun getReservas(): Response<ReservaResponse>

    @POST("/hacerReservas")
    suspend fun createReserva(
        @Body reserva: Reserva
    ): Response<ReservaResponse>

    @PUT("/modificarReserva/{idReserva}")
    suspend fun updateReserva(
        @Path("idReserva") idReserva: String,
        @Body reserva: Reserva
    ): Response<ReservaResponse>

    @DELETE("/cancelaReserva/{idReserva}")
    suspend fun deleteReserva(
        @Path("idReserva") idReserva: String
    ): Response<ReservaResponse>

    @GET("/reservas/{idReserva}")
    suspend fun getReserva(
        @Path("idReserva") idReserva: String
    ): Response<ReservaResponse>

    @GET("/reservas/fecha/{fecha}")
    suspend fun getReservaPorFecha(
        @Path("fecha") fecha: String
    ): Response<ReservaResponse>

    @POST("/signup")
    suspend fun signUp(@Body request: Request): Response<UserResponse>

    @POST("/signin")
    suspend fun signIn(@Body request: Request): Response<UserResponse>

    @GET("/criticas")
    suspend fun obtenerCriticas(): Response<CriticaResponse>

    @GET("/criticas/{idCritica}")
    suspend fun obtenerCritica(@Path("idCritica") idCritica: Int): Response<CriticaResponse>

    @POST("/hacerCriticas")
    suspend fun crearCritica(@Body critica: Critica): Response<CriticaResponse>

    @PUT("/actualizarCritica/{idCritica}")
    suspend fun actualizarCritica(
        @Path("idCritica") idCritica: String,
        @Body critica: Critica
    ): Response<CriticaResponse>

    @DELETE("/eliminarCritica/{idCritica}")
    suspend fun eliminarCritica(@Path("idCritica") idCritica: String): Response<CriticaResponse>

    @GET("/criticas/usuario/{idUsuario}")
    suspend fun obtenerCriticasUsuario(@Path("idUsuario") idUsuario: Int): Response<CriticaResponse>
}

