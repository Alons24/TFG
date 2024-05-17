package com.example.tfg.Retrofit

import com.example.tfg.Retrofit.DataClases.Reserva
import com.example.tfg.Retrofit.Response.ReservaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WebService {
    @GET("/reservas")
    suspend fun getReservas(): Response<ReservaResponse>
    @POST("/hacerReservas")
    suspend fun createReserva(
        @Body reserva: Reserva
    ): Response<ReservaResponse>

    @PUT("/actualizarReserva/{idReserva}")
    suspend fun updateReserva(
        @Path("idReserva") idReserva: Int,
        @Body reserva: Reserva
    ): Response<ReservaResponse>

    @DELETE("/cancelarReserva/{idReserva}")
    suspend fun deleteReserva(
        @Path("idReserva") idReserva: String
    ): Response<ReservaResponse>

}